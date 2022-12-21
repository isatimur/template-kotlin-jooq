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
       ('Mexican Unidad de Inversion (UDI)', 'MXV', '979', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145',
        NULL),
       ('Balboa', 'PAB', '590', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Som', 'KGS', '417', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('North Korean Won', 'KPW', '408', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Forint', 'HUF', '348', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Convertible Mark', 'BAM', '977', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('US Dollar (Next day)', 'USN', '997', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Yuan Renminbi', 'CNY', '156', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Saint Helena Pound', 'SHP', '654', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Afghani', 'AFN', '971', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Naira', 'NGN', '566', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Mexican Peso', 'MXN', '484', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Belize Dollar', 'BZD', '084', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Malagasy Ariary', 'MGA', '969', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Cuban Peso', 'CUP', '192', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Brazilian Real', 'BRL', '986', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kuwaiti Dinar', 'KWD', '414', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Zimbabwe Dollar', 'ZWL', '932', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Boliviano', 'BOB', '068', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('WIR Euro', 'CHE', '947', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Namibia Dollar', 'NAD', '516', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Moldovan Leu', 'MDL', '498', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Ethiopian Birr', 'ETB', '230', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Rial Omani', 'OMR', '512', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Denar', 'MKD', '807', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Ghana Cedi', 'GHS', '936', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lari', 'GEL', '981', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Danish Krone', 'DKK', '208', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Algerian Dinar', 'DZD', '012', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Azerbaijan Manat', 'AZN', '944', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('US Dollar', 'USD', '840', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Aruban Florin', 'AWG', '533', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('WIR Franc', 'CHW', '948', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Chilean Peso', 'CLP', '152', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Guarani', 'PYG', '600', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Malawi Kwacha', 'MWK', '454', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lilangeni', 'SZL', '748', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Peso Convertible', 'CUC', '931', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Ngultrum', 'BTN', '064', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Iraqi Dinar', 'IQD', '368', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Unidad de Fomento', 'CLF', '990', 4, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Colombian Peso', 'COP', '170', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Trinidad and Tobago Dollar', 'TTD', '780', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Libyan Dinar', 'LYD', '434', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Bolivar Soberano', 'VES', '928', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Hryvnia', 'UAH', '980', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Tala', 'WST', '882', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Tenge', 'KZT', '398', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Jamaican Dollar', 'JMD', '388', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lek', 'ALL', '008', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Egyptian Pound', 'EGP', '818', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Unidad Previsional', 'UYW', '927', 4, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Serbian Dinar', 'RSD', '941', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Swiss Franc', 'CHF', '756', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Solomon Islands Dollar', 'SBD', '090', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('CFP Franc', 'XPF', '953', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Philippine Peso', 'PHP', '608', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Tunisian Dinar', 'TND', '788', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Fiji Dollar', 'FJD', '242', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Barbados Dollar', 'BBD', '052', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Moroccan Dirham', 'MAD', '504', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Pound Sterling', 'GBP', '826', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('East Caribbean Dollar', 'XCD', '951', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Cayman Islands Dollar', 'KYD', '136', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Djibouti Franc', 'DJF', '262', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Uganda Shilling', 'UGX', '800', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Tanzanian Shilling', 'TZS', '834', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Sri Lanka Rupee', 'LKR', '144', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lebanese Pound', 'LBP', '422', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Uruguay Peso en Unidades Indexadas (UI)', 'UYI', '940', 0, '2022-12-07 16:00:15.145',
        '2022-12-07 16:00:15.145', NULL),
       ('South Sudanese Pound', 'SSP', '728', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Indian Rupee', 'INR', '356', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Pa`anga', 'TOP', '776', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Leone', 'SLE', '925', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Euro', 'EUR', '978', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Argentine Peso', 'ARS', '032', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Cabo Verde Escudo', 'CVE', '132', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Sudanese Pound', 'SDG', '938', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Costa Rican Colon', 'CRC', '188', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Bahamian Dollar', 'BSD', '044', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Rupiah', 'IDR', '360', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Rand', 'ZAR', '710', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Canadian Dollar', 'CAD', '124', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Surinam Dollar', 'SRD', '968', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Dong', 'VND', '704', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145'),
       ('Mauritius Rupee', 'MUR', '480', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('CFA Franc BCEAO', 'XOF', '952', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Turkish Lira', 'TRY', '949', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Somoni', 'TJS', '972', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kwanza', 'AOA', '973', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Bahraini Dinar', 'BHD', '048', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('UAE Dirham', 'AED', '784', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Iceland Krona', 'ISK', '352', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Rwanda Franc', 'RWF', '646', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kina', 'PGK', '598', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('New Israeli Sheqel', 'ILS', '376', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kenyan Shilling', 'KES', '404', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Guyana Dollar', 'GYD', '328', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Romanian Leu', 'RON', '946', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Dalasi', 'GMD', '270', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Gourde', 'HTG', '332', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Unidad de Valor Real', 'COU', '970', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Australian Dollar', 'AUD', '036', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Dobra', 'STN', '930', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lao Kip', 'LAK', '418', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Saudi Riyal', 'SAR', '682', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Malaysian Ringgit', 'MYR', '458', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Dominican Peso', 'DOP', '214', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Liberian Dollar', 'LRD', '430', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Belarusian Ruble', 'BYN', '933', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Guinean Franc', 'GNF', '324', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Yemeni Rial', 'YER', '886', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Loti', 'LSL', '426', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Zloty', 'PLN', '985', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Lempira', 'HNL', '340', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('New Taiwan Dollar', 'TWD', '901', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Riel', 'KHR', '116', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Ouguiya', 'MRU', '929', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Pakistan Rupee', 'PKR', '586', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Gibraltar Pound', 'GIP', '292', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Tugrik', 'MNT', '496', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Turkmenistan New Manat', 'TMT', '934', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Norwegian Krone', 'NOK', '578', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Quetzal', 'GTQ', '320', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('CFA Franc BEAC', 'XAF', '950', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Russian Ruble', 'RUB', '643', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Nakfa', 'ERN', '232', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Sol', 'PEN', '604', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Jordanian Dinar', 'JOD', '400', 3, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Bermudian Dollar', 'BMD', '060', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Mozambique Metical', 'MZN', '943', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('New Zealand Dollar', 'NZD', '554', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Hong Kong Dollar', 'HKD', '344', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Comorian Franc', 'KMF', '174', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Falkland Islands Pound', 'FKP', '238', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kuna', 'HRK', '191', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Syrian Pound', 'SYP', '760', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Nepalese Rupee', 'NPR', '524', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('El Salvador Colon', 'SVC', '222', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Vatu', 'VUV', '548', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Netherlands Antillean Guilder', 'ANG', '532', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Peso Uruguayo', 'UYU', '858', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Uzbekistan Sum', 'UZS', '860', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Taka', 'BDT', '050', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Armenian Dram', 'AMD', '051', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Seychelles Rupee', 'SCR', '690', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Brunei Dollar', 'BND', '096', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Burundi Franc', 'BIF', '108', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Cordoba Oro', 'NIO', '558', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Pataca', 'MOP', '446', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Congolese Franc', 'CDF', '976', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Mvdol', 'BOV', '984', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Yen', 'JPY', '392', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Zambian Kwacha', 'ZMW', '967', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Qatari Rial', 'QAR', '634', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Pula', 'BWP', '072', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Singapore Dollar', 'SGD', '702', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Bulgarian Lev', 'BGN', '975', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Baht', 'THB', '764', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Kyat', 'MMK', '104', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Won', 'KRW', '410', 0, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Czech Koruna', 'CZK', '203', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Swedish Krona', 'SEK', '752', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Rufiyaa', 'MVR', '462', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL),
       ('Iranian Rial', 'IRR', '364', 2, '2022-12-07 16:00:15.145', '2022-12-07 16:00:15.145', NULL);