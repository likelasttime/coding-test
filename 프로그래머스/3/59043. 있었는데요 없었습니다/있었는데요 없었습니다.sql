SELECT AI.ANIMAL_ID, AI.NAME
FROM ANIMAL_OUTS AO JOIN ANIMAL_INS AI ON AO.ANIMAL_ID = AI.ANIMAL_ID
WHERE AO.DATETIME < AI.DATETIME
ORDER BY AI.DATETIME ASC;