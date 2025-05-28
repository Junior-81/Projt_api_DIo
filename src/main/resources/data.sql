-- Dados iniciais para desenvolvimento
INSERT INTO tb_user (name) VALUES ('Devtraining DIO');

INSERT INTO tb_account (number, agency, balance, additional_limit) VALUES ('01234567-8', '0001', 2500.00, 1000.00);

INSERT INTO tb_card (number, available_limit) VALUES ('**** **** **** 1234', 2000.00);

INSERT INTO tb_feature (icon, description, user_id) VALUES 
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pix.svg', 'PIX', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/pay.svg', 'Pagar', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/transfer.svg', 'Transferir', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/account.svg', 'Conta Corrente', 1);

INSERT INTO tb_news (icon, description, user_id) VALUES 
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/credit.svg', 'O Santander tem soluções de crédito sob medida pra você. Confira!', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/insurance.svg', 'Santander Seguro Casa, seu flar de casa protegido do jeito que você sempre quis.', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/investment.svg', 'Eai, pronto pra começar? Investimentos digitais Santander. Mais rendimento pro seu dinheiro.', 1),
('https://digitalinnovationone.github.io/santander-dev-week-2023-api/icons/loan.svg', 'Crediário Santander é a opção de crédito certa pra você ter a liberdade de fazer o que quiser.', 1);
