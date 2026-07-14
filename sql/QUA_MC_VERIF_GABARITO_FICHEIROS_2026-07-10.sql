-- Anexos dos registos de verificação de gabaritos (Meios de Controlo)
-- Paridade com QUA_MC_MOV_CALIB_EQUIP_FICHEIROS dos equipamentos.
IF OBJECT_ID('QUA_MC_MOV_VERIF_GABARITO_FICHEIROS', 'U') IS NULL
CREATE TABLE QUA_MC_MOV_VERIF_GABARITO_FICHEIROS (
    ID                          INT IDENTITY(1,1) PRIMARY KEY,
    ID_VERIF_GABARITO           INT REFERENCES QUA_MC_MOV_VERIF_GABARITO(ID_VERIF_GABARITO),
    TIPO_ANEXO                  NVARCHAR(20),
    DESCRICAO                   NVARCHAR(500),
    NOME                        NVARCHAR(500),
    CAMINHO                     NVARCHAR(500),
    TIPO                        NVARCHAR(20),
    DATATYPE                    NVARCHAR(100),
    TAMANHO                     INT,
    FICHEIRO_1                  NVARCHAR(MAX),
    FICHEIRO_2                  NVARCHAR(MAX),
    UTZ_CRIA                    INT, DATA_CRIA   DATE,
    UTZ_MODIF                   INT, DATA_MODIF  DATE,
    UTZ_ANULA                   INT, DATA_ANULA  DATE,
    ATIVO                       BIT DEFAULT 1
);
