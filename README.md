# сurrencies

Черновик справочника валют, как шаблон сервиса на стеке SpringBoot, RSocket, PostgreSQL, r2dbc, jooq, liquibase

### структура данных

```sql
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
```

```sql
create table iso_currencies
(
    currency        VARCHAR UNIQUE           NOT NULL,
    alphabetic_code VARCHAR UNIQUE           NOT NULL,
    numeric_code    VARCHAR UNIQUE           NOT NULL,
    minor_unit      INTEGER                  NULL,

    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    archived_at     TIMESTAMP WITH TIME ZONE NULL,

    CONSTRAINT iso_currencies__alphabetic_code__pk PRIMARY KEY (alphabetic_code)

);

COMMENT ON TABLE iso_currencies IS 'Справочник валют ISO 4217';

COMMENT ON COLUMN iso_currencies.currency IS 'Наименование валюты';
COMMENT ON COLUMN iso_currencies.alphabetic_code IS 'Буквенный код валюты';
COMMENT ON COLUMN iso_currencies.numeric_code IS 'Числовой код валюты';
COMMENT ON COLUMN iso_currencies.minor_unit IS 'Разряд минорной единицы валюты';

COMMENT ON COLUMN iso_currencies.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN iso_currencies.updated_at IS 'Дата и время обновления записи';
COMMENT ON COLUMN iso_currencies.archived_at IS 'Дата и время удаления записи';

INSERT INTO iso_currencies (currency, alphabetic_code, numeric_code, minor_unit, created_at, updated_at, archived_at)
VALUES ('Somali Shilling', 'SOS', '706', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ...
```

### миграция структуры данных

для миграции базы данных используется liquibase
путь для миграций:

resources/db/changelog.yaml

### контроллеры

А данном сервисе используются реактивные RSocket контроллеры типа request-response.
А также http контроллеры

_запрос на добавление валюты_

```kotlin
    @PostMapping
fun addCurrency(@RequestBody currency: Currency): Mono<Currency>
```
```kotlin
    @MessageMapping("addCurrency")
fun addCurrency(@RequestBody currency: Currency): Mono<Currency>
```

_запрос карточки валюты_

```kotlin
    @GetMapping("{id}")
fun getCurrencyCard(@PathVariable("id") id: String): Mono<Currency>
```
```kotlin
    @MessageMapping("getCurrencyCard")
fun getCurrencyCard(id: UUID): Mono<Currency>
```

_запрос всех доступных для использования на платформе валют_

```kotlin
    @GetMapping("")
fun searchCurrencies(
    @RequestParam(name = "page") page: Int,
    @RequestParam(name = "size") size: Int
): Flux<Currency>
```
```kotlin
    @MessageMapping("searchCurrencies")
fun searchCurrencies(pageable: Pageable): Flux<Currency>
```

_запрос всех еще не используемых доступных валют на платформе_

```kotlin
    @GetMapping("absent-list")
fun getAbsentCurrenciesList(
    @RequestParam(name = "page") page: Int,
    @RequestParam(name = "size") size: Int
): Flux<IsoCurrency>
```
```kotlin
    @MessageMapping("getAbsentCurrenciesList")
fun getAbsentCurrenciesList(pageable: Pageable): Flux<IsoCurrency>
```

_обновление карточки валюты_

```kotlin
    @PostMapping("/{id}")
fun updateCurrency(@PathVariable("id") id: String, @RequestBody currency: Currency): Mono<Currency> 
```
```kotlin
    @MessageMapping("updateCurrency")
fun updateCurrency(currency: Currency): Mono<Currency> 
```

**dto**

```kotlin
// валюта
data class Currency(
    val id: UUID? = null,
    val name: String? = null,
    val isoCode: String? = null,
    val description: String? = null,
    val isActive: Boolean? = false,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null,
    val archivedAt: OffsetDateTime? = null
)

// валюта из справочника
data class IsoCurrency(
    val currency: String? = null,
    val alphabeticCode: String? = null,
    val numericCode: String? = null,
    val minorUnit: Int? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null,
    val archivedAt: OffsetDateTime? = null
)

// класс запроса данных с пагинацией
data class Pageable(
    val page: Int,
    val size: Int
)
```

### сборка проекта

система сборки gradle

перед копмиляцией кода проекта выполняется таск _generateJooq_.
Задача таска поднять базу данных, применить к ней миграции и запустить для обновленной базы задачу по генерации классов,
отображающих базу данных в коде проекта. Данные классы добавляют с build папку проекта и их теперь можно использовать в
коде.

### docker-compose

для локального тестирования сервиса используя докер композ можно поднять следующие образы:

база данных postgres:

* port: 5432
* user: postgres
* password: 123456
