-- DROP TABLE region     CASCADE CONSTRAINTS;

CREATE TABLE region
( region_id      VARCHAR2(100)
      CONSTRAINT  region_id_nn NOT NULL
    , region_name    VARCHAR2(200)
);

CREATE UNIQUE INDEX reg_id_pk
    ON region (region_id);

ALTER TABLE region
    ADD ( CONSTRAINT reg_id_pk
       		 PRIMARY KEY (region_id)
    ) ;

INSERT INTO region VALUES
    ( 'ap-sydney-1'
    , 'オーストラリア東部(シドニー)'
    );
INSERT INTO region VALUES
    ( 'ap-melbourne-1'
    , 'オーストラリア南東部(メルボルン)'
    );
INSERT INTO region VALUES
    ( 'sa-saopaulo-1'
    , 'ブラジル東部(サンパウロ)'
    );
INSERT INTO region VALUES
    ( 'ap-osaka-1'
    , '日本中央部(大阪)'
    );

INSERT INTO region VALUES
    ( 'ap-tokyo-1'
    , '日本東部(東京)'
    );

COMMIT;