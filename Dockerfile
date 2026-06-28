# Sử dụng image chính thức của PostgreSQL
FROM postgres:15

# Thiết lập biến môi trường cho database
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=123456
ENV POSTGRES_DB=schooldb

# Copy file SQL hoặc script init vào thư mục docker-entrypoint-initdb.d
# PostgreSQL sẽ tự động chạy các file này khi container khởi tạo lần đầu
COPY init.sql /docker-entrypoint-initdb.d/
