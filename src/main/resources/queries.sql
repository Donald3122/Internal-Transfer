-- 1.Задание: Топ-5 счетов по количеству транзакций за последний месяц
SELECT
    t.debit AS account_number,
    COUNT(*) AS transaction_count
FROM public.transactions t
WHERE t.created >= NOW() - INTERVAL '1 month'
GROUP BY t.debit
ORDER BY transaction_count DESC
    LIMIT 5;


-- 2.Задание: Общая сумма переводов за выбранный период
SELECT
    t.debit_currency_id AS currency_id,
    SUM(t.from_amount) AS total_amount
FROM public.transactions t
WHERE t.created >= '2026-03-01 00:00:00'
  AND t.created <= '2026-03-10 23:59:59'
GROUP BY t.debit_currency_id
ORDER BY t.debit_currency_id;


--3.Задание: Счита с отрицательным балансом
SELECT
    a.account_number,
    a.balance
FROM public.accounts a
WHERE a.balance < 0;


--4.Задание: Добавление индекса
CREATE INDEX idx_transactions_created ON public.transactions (created);

--5.Задание: Индекс в базе данных — это специальная структура данных, которая хранит ссылки на строки таблицы в определённом порядке. Можно сравнить с оглавлением книги:
--В книге ты не листаешь все страницы, чтобы найти нужную тему.
--Ты смотришь оглавление → находишь страницу → быстро переходишь к ней.
--Так же работает индекс: вместо полного сканирования всей таблицы (FULL TABLE SCAN), СУБД ищет строки по индексу, что намного быстрее.
--Таким образом индексы просто не обходимы для работы с таблицами где хранятся большие обьёмы данных.