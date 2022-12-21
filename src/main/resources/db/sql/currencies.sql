create table currencies
(
    id          UUID        NOT NULL,
    name        VARCHAR   NOT NULL,
    iso_code    VARCHAR   NOT NULL,
    description VARCHAR   NOT NULL,
    is_active   BOOLEAN   NOT NULL DEFAULT FALSE,

    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    archived_at TIMESTAMP WITH TIME ZONE NULL,

    CONSTRAINT currencies__id__pk PRIMARY KEY (id)

);

CREATE UNIQUE INDEX currencies__name__ui ON currencies (name);
CREATE UNIQUE INDEX currencies__iso_code__ui ON currencies (iso_code);

COMMENT ON TABLE currencies                            IS 'Валюты, разрешённые для использования в системе';
COMMENT ON COLUMN currencies.id                        IS 'Идентификатор валюты';
COMMENT ON COLUMN currencies.name                      IS 'Наименование валюты по ISO 4217';
COMMENT ON COLUMN currencies.iso_code                  IS 'Буквенный код валюты по ISO 4217';
COMMENT ON COLUMN currencies.description               IS 'Описание валюты';
COMMENT ON COLUMN currencies.is_active                 IS 'Признак активации валюты';
COMMENT ON COLUMN currencies.created_at                IS 'Дата и время создания записи';
COMMENT ON COLUMN currencies.updated_at                IS 'Дата и время обновления записи';
COMMENT ON COLUMN currencies.archived_at               IS 'Дата и время удаления записи';