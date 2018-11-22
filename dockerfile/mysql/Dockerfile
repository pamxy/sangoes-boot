FROM mysql:5.7.23

#将所需文件放到容器中
COPY setup.sh /mysql/setup.sh
COPY boot.sql /mysql/boot.sql
COPY my.cnf /etc/mysql/conf.d/mysqlutf8.cnf

#设置容器启动时执行的命令
CMD ["bash", "/mysql/setup.sh"]