use mysql;

select host, user
from user;

create user sangoes
  identified by 'Sangoes123456';

grant all on `boot`.* to sangoes@'%'
identified by 'Sangoes123456'
with grant option;

flush privileges;

-- privileges.sql