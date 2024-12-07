INSERT INTO "doacao"("codigo", "data", "hora", "volume", "situacao", "doador_codigo")
VALUES
	(1, NOW()::DATE, NOW()::TIME, 212.55, TRUE, 1),
	(2, NOW()::DATE, NOW()::TIME, 310.20, TRUE, 1),
	(3, NOW()::DATE, NOW()::TIME, 110.99, TRUE, 2),
	(4, NOW()::DATE, NOW()::TIME, 200.00, FALSE, 3),
	(5, NOW()::DATE, NOW()::TIME, 173.82, TRUE, 1),
	(6, NOW()::DATE, NOW()::TIME, 400.50, TRUE, 3),
	(7, NOW()::DATE, NOW()::TIME, 019.76, TRUE, 1),
	(8, NOW()::DATE, NOW()::TIME, 099.10, FALSE, 2);
