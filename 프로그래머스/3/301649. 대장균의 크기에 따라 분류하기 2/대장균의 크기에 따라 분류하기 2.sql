SELECT DATA_TABLE.ID, 
CASE 
    WHEN DATA_TABLE.PER <= 0.25 THEN "CRITICAL"
    WHEN DATA_TABLE.PER <= 0.5 THEN "HIGH"
    WHEN DATA_TABLE.PER <= 0.75 THEN "MEDIUM"
    ELSE "LOW"
END AS COLONY_NAME
FROM (SELECT ID, PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS PER
      FROM ECOLI_DATA) AS DATA_TABLE
ORDER BY ID;
