package com.example.springbootjaeger.actor;

import com.alibaba.ttl.TransmittableThreadLocal;
import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.noop.NoopSpan;
import io.opentracing.util.GlobalTracer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

import static com.example.springbootjaeger.actor.ActorConstants.*;

@Component
@Slf4j
public class ActorContextHolder {
    private ActorContextHolder() {
    }

    private static final TransmittableThreadLocal<Actor> actorHolder = new TransmittableThreadLocal<>();
    private static final TransmittableThreadLocal<LinkedBlockingDeque<Actor>> actorQueue = new TransmittableThreadLocal<>();

    public static void setActor(Actor actor) {
        if (hasTracer()) {
            setTracerActor(actor);
        } else {
            setLocalActor(actor);
        }
    }

    public static Actor getActor() {
        if (hasTracer()) {
            return getTracerActor();
        } else {
            return getLocalActor();
        }
    }

    public static void resetActor() {
        if (hasTracer()) {
            resetTracerActor();
        } else {
            resetLocalActor();
        }
    }

    public static void addActorsFirst(Actor actor) {
        if (Objects.isNull(actor)) {
            return;
        }
        LinkedBlockingDeque<Actor> actors = actorQueue.get();
        if (Objects.isNull(actors)) {
            actors = new LinkedBlockingDeque<>();
        }
        Actor previousActor = getActor();
        actors.addFirst(previousActor);
        actorQueue.set(actors);
        setActor(actor);
    }

    public static void removeActorsFirst() {
        LinkedBlockingDeque<Actor> actors = actorQueue.get();
        if (Objects.isNull(actors)) {
            setActor(null);
            return;
        }
        try {
            Actor previousActor = actors.removeFirst();
            actorQueue.set(actors);
            setActor(previousActor);
        } catch (NoSuchElementException ex) {
            if (log.isDebugEnabled()) {
                log.info("[ActorContextHolder] context actors is empty ");
            }
        }
    }

    public static void runAs(Actor actor, Runnable runnable) {
        addActorsFirst(actor);
        runnable.run();
        removeActorsFirst();
    }

    public static <T> T runAs(Actor actor, Supplier<T> supplier) {
        addActorsFirst(actor);
        T result = supplier.get();
        removeActorsFirst();
        return result;
    }

    private static boolean hasTracer() {
        return !(GlobalTracer.get().activeSpan() instanceof NoopSpan);
    }

    private static Actor getLocalActor() {
        return actorHolder.get();
    }

    private static Actor getTracerActor() {
        Span activeSpan = GlobalTracer.get().scopeManager().activeSpan();
        if (Objects.isNull(activeSpan)) {
            return null;
        }
        Actor actor = Actor.builder().build();
        actor.setActorId(activeSpan.getBaggageItem(X_ACTOR_ID));
        actor.setTenantId(activeSpan.getBaggageItem(X_TENANT_ID));
        return actor;
    }

    private static void setLocalActor(Actor actor) {
        actorHolder.set(actor);
        putLocalMDCContext(actor);
    }

    private static void setTracerActor(Actor actor) {
        ScopeManager scopeManager = GlobalTracer.get().scopeManager();
        Span parentSpan = scopeManager.activeSpan();
        Tracer.SpanBuilder spanBuilder = GlobalTracer.get().buildSpan("set-actor");
        Span actorSpan;
        if (Objects.nonNull(parentSpan)) {
            actorSpan = spanBuilder.asChildOf(parentSpan).start();
        } else {
            actorSpan = spanBuilder.start();
        }
        actorSpan.setBaggageItem(X_ACTOR_ID, actor.getActorId());
        actorSpan.setBaggageItem(X_TENANT_ID, actor.getTenantId());
        scopeManager.activate(actorSpan);
        actorSpan.finish();
    }

    private static void resetLocalActor() {
        actorHolder.remove();
        cleanLocalMDCContext();
    }

    private static void resetTracerActor() {
        Span activeSpan = GlobalTracer.get().activeSpan();
        if (Objects.isNull(activeSpan)) {
            return;
        }
        activeSpan.setBaggageItem(X_ACTOR_ID, null);
        activeSpan.setBaggageItem(X_TENANT_ID, null);
    }

    private static void putLocalMDCContext(Actor actor) {
        if (Objects.nonNull(actor)) {
            MDC.put(MDC_ACTOR_ID, actor.getActorId());
            MDC.put(MDC_TENANT_ID, actor.getTenantId());
        }
    }

    private static void cleanLocalMDCContext() {
        MDC.put(MDC_ACTOR_ID, null);
        MDC.put(MDC_TENANT_ID, null);
    }

}