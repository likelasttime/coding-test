-- 코드를 입력하세요
SELECT PRODUCT_CODE, SUM(PRICE * SALES_AMOUNT) SALES
FROM OFFLINE_SALE O INNER JOIN PRODUCT P ON O.PRODUCT_ID = P.PRODUCT_ID
GROUP BY PRODUCT_CODE
ORDER BY SUM(PRICE * SALES_AMOUNT) DESC, PRODUCT_CODE;
