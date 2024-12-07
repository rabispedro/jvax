CREATE TABLE IF NOT EXISTS "doacao" (
	"codigo" BIGSERIAL PRIMARY KEY,
	"data" DATE NOT NULL,
	"hora" TIME NOT NULL,
	"volume" NUMERIC(10, 4) NOT NULL,
	"situacao" BOOLEAN NOT NULL,
	"doador_codigo" BIGINT NOT NULL,

	CONSTRAINT "fk_doador" FOREIGN KEY("doador_codigo") REFERENCES "doador"("codigo")  
);
