# Как пользоваться программой

Тут в целом всё понятно: в начале надо залогиниться либо создать аккаунт, позже можно будет разлогинться.

Админ может просматривать меню, добавлять и удалять блюда (указывать их стоимость и сложность), просматривать все заказы и выручку.

Клиент может делать заказ (добавлять блюда, в том числе несколько одинаковых), подтверждать (отправлять на готовку) и отменять на этапе создания и готовки. После приготовления заказа пользователь видит соответсвующее сообщение. Если пользователь вышел из программы и зашёл снова, то заказа всё равно будет доведён до конца, т.к. каждый заказ хранит в себе информацию о времени его создания.
Также клиент может просматривать текущие заказы и те, которые нужно оплатить.

Уже есть аккаунты админа и клиента:
- admin: 12345678
- client: qwerty


# Как написана программа

В программе из шаблонов проектирования используется, например, билдер заказа (чтобы, пока клиент создаёт заказ, его можно было изменять, а затем сбилдить и отправить на готовку). Также, думаю, классы для записи и чтения файлов можно отнести к 'Стратегии'.

Вся информация хранится в JSON-файлах. Для внедрения зависимостей используется Dagger 2.
