SELECT HD.DEPT_ID, DEPT_NAME_EN, ROUND(AVG(SAL)) AS AVG_SAL
FROM HR_DEPARTMENT AS HD JOIN HR_EMPLOYEES AS HE
ON HD.DEPT_ID = HE.DEPT_ID
GROUP BY HD.DEPT_ID
ORDER BY AVG_SAL DESC;