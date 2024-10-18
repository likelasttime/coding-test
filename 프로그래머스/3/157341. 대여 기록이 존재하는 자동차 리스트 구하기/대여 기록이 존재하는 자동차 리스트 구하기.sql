SELECT DISTINCT CRC.CAR_ID
FROM CAR_RENTAL_COMPANY_CAR CRC JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY CRCH
ON CRC.CAR_ID = CRCH.CAR_ID
WHERE CAR_TYPE = '세단' AND MONTH(START_DATE) = 10
ORDER BY CRC.CAR_ID DESC;