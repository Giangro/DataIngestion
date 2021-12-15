/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  alex
 * Created: 14 dic 2021
 */

--DROP TABLE [TRACCE].[NodoAllRiscontriCodiciRaccomandata]
CREATE TABLE [TRACCE].[NodoAllRiscontriCodiciRaccomandata](
        [Id] [bigint] IDENTITY(1,1) NOT NULL,
        [CodiceRaccomandata] [bigint] NOT NULL,
        [TipoRiscontro] [varchar](2) NOT NULL,
 CONSTRAINT [PK_NodoAllRiscontriCodiciRaccomandata_Id] PRIMARY KEY CLUSTERED
(
        [Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

--DROP TABLE [TRACCE].[NodoInputCodiciRaccomandata]
CREATE TABLE [TRACCE].[NodoInputCodiciRaccomandata](
        [Id] [bigint] IDENTITY(1,1) NOT NULL,
        [CodiceRaccomandata] [bigint] NOT NULL,
 CONSTRAINT [PK_NodoInputCodiciRaccomandata_Id] PRIMARY KEY CLUSTERED
(
        [Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

CREATE NONCLUSTERED INDEX [IX_NodoInputCodiciRaccomandata_CodiceRaccomandata] ON [TRACCE].[NodoInputCodiciRaccomandata]
(
	[CodiceRaccomandata] ASC
)

CREATE NONCLUSTERED INDEX [IX_NodoAllRiscontriCodiciRaccomandata_CodiceRaccomandata_TipoRiscontro] 
  ON [TRACCE].[NodoAllRiscontriCodiciRaccomandata]
(
	[CodiceRaccomandata] ASC,
    [TipoRiscontro] ASC
)

SELECT COUNT(ni.CodiceRaccomandata) FROM TRACCE.NodoInputCodiciRaccomandata ni
WHERE ni.CodiceRaccomandata IN (
    SELECT CodiceRaccomandata FROM TRACCE.ListaCodici40k
)

SELECT COUNT(na.CodiceRaccomandata) FROM TRACCE.NodoAllRiscontriCodiciRaccomandata na
INNER JOIN TRACCE.ListaCodici40k lc ON na.CodiceRaccomandata = lc.CodiceRaccomandata
WHERE na.TipoRiscontro = '00'
GO 