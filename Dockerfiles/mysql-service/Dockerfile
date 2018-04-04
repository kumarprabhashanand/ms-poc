FROM mysql
ENV MYSQL_DATABASE=ms_poc
ENV MYSQL_ROOT_PASSWORD=password
ADD ms-poc.sql /docker-entrypoint-initdb.d
EXPOSE 3306