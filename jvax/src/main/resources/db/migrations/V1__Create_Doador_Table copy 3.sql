CREATE TABLE IF NOT EXISTS "doador" (
	"codigo" BIGSERIAL PRIMARY KEY,
	"nome" VARCHAR NOT NULL,
	"cpf" VARCHAR(11) UNIQUE NOT NULL,
	"contato" VARCHAR(11) NOT NULL,
	"situacao" BOOLEAN NOT NULL,
	"tipo_sanguineo" VARCHAR,
	"rh" BOOLEAN
);
