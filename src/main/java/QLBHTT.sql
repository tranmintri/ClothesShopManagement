create database QLBHTT
go
use QLBHTT
go

--use master
--drop database QLBHTT

-- sản phẩm
CREATE TABLE sanPham(
	maSP nvarchar(10) not null,
	tenSP nvarchar(100) not null,
	giaNhap bigint not null,
	hinhAnh nvarchar(50) not null,
	soLuong int not null,
	moTa nText not null,
	maChatLieu nvarchar(10) not null,
	maKieuDang nvarchar(10) not null,
	maKichThuoc nvarchar(10) not null,
	maMauSac nvarchar(10) not null,
	maXuatXu nvarchar(10) not null,
	maPhanLoai nvarchar(10) not null,
	maNCC nvarchar(10) not null,
	maKhuyenMai nvarchar(10) not null
	)

--Phân Loại
CREATE TABLE PhanLoai(
    maPhanLoai nvarchar(10) not null,
	loaiSP nvarchar(10) not null
	)

--Nhà cung cấp

Create table NhaCungCap
(  
    maNCC nvarchar(10) not null, 
	tenNCC nvarchar(100) not null, 
	diaChi nvarchar(200) not null, 
	email nvarchar(50) not null unique, 
	sdt nvarchar(20) not null unique, 
);

--Nhân viên

create table nhanvien(
	maNhanVien nvarchar(10) not null, 
	hoVaTen nvarchar(30) not null,
	ngaySinh date not null,
	diaChi nvarchar(200) not null,
	sdt nvarchar(10) not null unique,
	gioiTinh bit not null, --0: nam --1: nu
	luong int not null,
	email nvarchar(30) not null unique,
	chucvu nvarchar(20) not null,
	OTP nvarchar(25) null,
	expriedAt datetime null,
	isDeleted bit null
)


--ACCOUNT
create table TaiKhoan(
	tenTaiKhoan nvarchar(20) not null,
	matKhau nvarchar(20) not null,
	loaiTaiKhoan bit not null, --1: quan ly -- 0: nhan vien
	maNhanVien nvarchar(10) not null unique, 
	isDeleted bit null
)

--HÓA ĐƠN
create table HoaDon(
	maHoaDon nvarchar(50) not null,
	ngaylap date not null,
	maNhanVien nvarchar(10) not null,
	maKhachHang nvarchar(10) not null
)

--CHI TIẾT HÓA ĐƠN
create table ChiTietHoaDon(
	maSP nvarchar(10) not null,
	maHoaDon nvarchar(50) not null,
	soLuong int not null,
	thanhTien bigint not null
)


--KHÁCH HÀNG
create table khachHang(
	maKhachHang nvarchar(10) not null,
	hoVaTen nvarchar(30) not null,
	email nvarchar(30) not null unique,
	sdt nvarchar(10) not null unique, 
	gioiTinh bit not null --0: nam 1: nu
)

--Khuyến mãi
create table KhuyenMai(
	maKhuyenMai nvarchar(10) not null,
	phanTramKhuyenMai int not null,
	ngayBatDau date not null,
	soLuong int not null
)
create table XuatXu(
	maXuatXu nvarchar(10) not null,
	noiXuatXu nvarchar(20) not null
)
create table ChatLieu(
	maChatLieu nvarchar(10) not null,
	chatLieu nvarchar(20) not null
)
create table MauSac(
	maMauSac nvarchar(10) not null,
	mauSac nvarchar(20) not null
)
create table KichThuoc(
	maKichThuoc nvarchar(10) not null,
	kichThuoc nvarchar(10) not null
)
create table KieuDang(
	maKieuDang nvarchar(10) not null,
	kieuDang nvarchar(20) not null
)

--PRIMARY KEY

alter table chiTietHoaDon
add constraint maSp_maHD primary key(maSP,maHoaDon)

alter table sanPham
add primary key(maSP)

alter table PhanLoai
add primary key(maPhanLoai)

alter table nhacungcap
add primary key(maNCC)

alter table nhanvien
add primary key(maNhanVien)

alter table TaiKhoan
add primary key(tenTaiKhoan)

alter table hoadon
add primary key(maHoaDon)

alter table KhachHang
add primary key(maKhachHang)

alter table khuyenMai
add primary key(maKhuyenMai)

alter table XuatXu
add primary key(maXuatXu)

alter table ChatLieu
add primary key(maChatLieu)

alter table MauSac
add primary key(maMauSac)

alter table KichThuoc
add primary key(maKichThuoc)

alter table KieuDang
add primary key(maKieuDang)


--FOREIGN KEY

alter table sanPham
add constraint FK_sanPham_KhuyenMai foreign key(maKhuyenMai) references khuyenMai(maKhuyenMai)

alter table sanPham
add constraint FK_sanPham_phanLoai foreign key(maPhanLoai) references PhanLoai(maPhanLoai)

alter table sanPham
add constraint FK_sanPham_xuatXu foreign key(maXuatXu) references XuatXu(maXuatXu)

alter table sanPham
add constraint FK_sanPham_chatLieu foreign key(maChatLieu) references ChatLieu(maChatLieu)

alter table sanPham
add constraint FK_sanPham_mauSac foreign key(maMauSac) references MauSac(maMauSac)

alter table sanPham
add constraint FK_sanPham_kichThuoc foreign key(maKichThuoc) references KichThuoc(maKichThuoc)

alter table sanPham
add constraint FK_sanPham_kieuDang foreign key(maKieuDang) references KieuDang(maKieuDang)

alter table sanPham
add constraint FK_sanPham_nhaCungCap foreign key(maNCC) references NhaCungCap(maNCC)

alter table TaiKhoan 
add constraint FK_taiKhoan_nhanVien foreign key(maNhanVien) references nhanvien(maNhanVien)

alter table hoadon
add constraint FK_hoaDon_nhanVien foreign key(maNhanVien) references NhanVien(maNhanVien)

alter table hoadon
add constraint FK_hoaDon_khachHang foreign key(maKhachHang) references khachhang(maKhachHang)

alter table chitiethoadon
add constraint FK_cthd_hd foreign key(maHoaDon) references hoadon(maHoaDon)

alter table chitiethoadon
add constraint FK_cthd_sp foreign key(maSP) references sanPham(maSP)

ALTER TABLE nhanvien
ADD DEFAULT(0) FOR OTP

ALTER TABLE nhanvien
ADD DEFAULT(0) FOR expriedAt

ALTER TABLE nhanvien
ADD DEFAULT(0) FOR isDeleted

ALTER TABLE TaiKhoan
ADD DEFAULT(0) FOR isDeleted

--insert values

----Them gia tri vao table mau sac
insert into MauSac values ('MS0001',N'Trắng');
insert into MauSac values ('MS0002',N'Đen');
insert into MauSac values ('MS0003',N'Hồng');
insert into MauSac values ('MS0004',N'Xanh Trắng');
insert into MauSac values ('MS0005',N'Kem');

----Them gia tri vao table phan loai
insert into PhanLoai values ('PL0001',N'Áo');
insert into PhanLoai values ('PL0002',N'Quần');
insert into PhanLoai values ('PL0003',N'Nón');

----Them gia tri vao table chat lieu
insert into ChatLieu values ('CL0001',N'Cotton 2 chiều')
insert into ChatLieu values ('CL0002',N'Vải Nỉ')
insert into ChatLieu values ('CL0003',N'Da')
insert into ChatLieu values ('CL0004',N'Kaki cotton')
insert into ChatLieu values ('CL0005',N'Vải gió dù')

----Them gia tri vao table kich thuoc
insert into KichThuoc values ('KT0001',N'S');
insert into KichThuoc values ('KT0002',N'M');
insert into KichThuoc values ('KT0003',N'L');
insert into KichThuoc values ('KT0004',N'XL')
insert into KichThuoc values ('KT0005',N'XXL')
insert into KichThuoc values ('KT0006',N'XXXL')

----Them gia tri vao table kieu dang
insert into KieuDang values ('KD0001',N'Áo Thun T-shirts')
insert into KieuDang values ('KD0002',N'Áo Hoodie')
insert into KieuDang values ('KD0003',N'Quần Short')
insert into KieuDang values ('KD0004',N'Áo khoác')
insert into KieuDang values ('KD0005',N'Nón kết')

----Them gia tri vao table xuat xu
insert into XuatXu values ('XX0001',N'Việt Nam')
insert into XuatXu values ('XX0002',N'Trung Quốc')

----Them gia tri vao table nha cung cap
insert into NhaCungCap values ('NCC001',N'Công Ty TNHH HADES',N'45 Phan Chu Trinh, P. Bến Thành, Quận 1, TP. Hồ Chí Minh','support@hades.vn','02873011021')
insert into NhaCungCap values ('NCC002',N'Công Ty TNHH PARADOX Việt Nam',N'995 Nguyễn Trãi, Phường 14, Quận 5, TP. Hồ Chí Minh','info@paradoxworldwide.com','0344800808')
insert into NhaCungCap values ('NCC003',N'Cửa hàng SuperStorm Store',N'35/2k ấp Hưng Lân, Bà Điểm, Hóc Môn, TP. Hồ Chí Minh','yatuan99@gmail.com','0386928958')

insert into NhaCungCap values ('NCC004',N'Công ty cổ phần may Việt Tiến',N'07 Lê Minh Xuân, Q. Tân Bình, TP. Hồ Chí Minh','viettien@viettien.com.vn','02838640800')
insert into NhaCungCap values ('NCC005',N'Công ty TNHH May Thêu Giày An Phước',N'100/11-12 An Dương Vương, P.9, Q.5, Tp.Hồ Chí Minh','maydodongphuc@anphuoc.com.vn','02838350059')

----Them gia tri vao table khuyen mai
insert into KhuyenMai values ('KM0001','0','2022-09-26',10)
insert into KhuyenMai values ('KM0002','10','2022-09-26',10)
insert into KhuyenMai values ('KM0003','20','2022-09-26',10)
insert into KhuyenMai values ('KM0004','30','2022-09-26',10)
insert into KhuyenMai values ('KM0005','40','2022-09-26',10)

----them gia tri vao table san pham
insert into sanPham values ('SP0001',N'ON CLOUD NINE TEE',380000,'D:\hinhAnh\sp0001.png',1000,N'Mo ta','CL0001','KD0001','KT0001','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0002',N'ON CLOUD NINE TEE',380000,'D:\hinhAnh\sp0001.png',1000,N'Mo ta','CL0001','KD0001','KT0002','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0003',N'ON CLOUD NINE TEE',380000,'D:\hinhAnh\sp0001.png',1000,N'Mo ta','CL0001','KD0001','KT0003','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0004',N'Áo Hoodie Mũ Trùm Unisex Vải Nỉ CHRONICLE',650000,'D:\hinhAnh\sp0004.jpg',1000,N'Mo ta','CL0002','KD0002','KT0002','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0005',N'Áo Hoodie Mũ Trùm Unisex Vải Nỉ CHRONICLE',650000,'D:\hinhAnh\sp0004.jpg',1000,N'Mo ta','CL0002','KD0002','KT0003','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0006',N'Áo Hoodie Mũ Trùm Unisex Vải Nỉ CHRONICLE',650000,'D:\hinhAnh\sp0004.jpg',1000,N'Mo ta','CL0002','KD0002','KT0004','MS0001','XX0001','PL0001','NCC001','KM0001')
insert into sanPham values ('SP0007',N'Quần short Paradox FRISKY EMBROIDERY SHORTS',380000,'D:\hinhAnh\sp0007.jpg',1000,N'Mo ta','CL0002','KD0003','KT0001','MS0002','XX0001','PL0002','NCC002','KM0001')
insert into sanPham values ('SP0008',N'Quần short Paradox FRISKY EMBROIDERY SHORTS',380000,'D:\hinhAnh\sp0007.jpg',1000,N'Mo ta','CL0002','KD0003','KT0002','MS0002','XX0001','PL0002','NCC002','KM0001')
insert into sanPham values ('SP0009',N'Quần short Paradox FRISKY EMBROIDERY SHORTS',380000,'D:\hinhAnh\sp0007.jpg',1000,N'Mo ta','CL0002','KD0003','KT0003','MS0002','XX0001','PL0002','NCC002','KM0001')
insert into sanPham values ('SP0010',N'Áo khoác bomber',180000,'D:\hinhAnh\sp0010.jpg',1000,N'Mo ta','CL0002','KD0004','KT0001','MS0004','XX0002','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0011',N'Áo khoác bomber',180000,'D:\hinhAnh\sp0010.jpg',1000,N'Mo ta','CL0002','KD0004','KT0002','MS0004','XX0002','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0012',N'Áo khoác bomber',180000,'D:\hinhAnh\sp0010.jpg',1000,N'Mo ta','CL0002','KD0004','KT0003','MS0004','XX0002','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0013',N'Nón SBTC Logo',189000,'D:\hinhAnh\sp0013.png',1000,N'Mo ta','CL0004','KD0005','KT0001','MS0003','XX0001','PL0003','NCC003','KM0001')
insert into sanPham values ('SP0014',N'Nón SBTC Logo',189000,'D:\hinhAnh\sp0013.png',1000,N'Mo ta','CL0004','KD0005','KT0002','MS0003','XX0001','PL0003','NCC003','KM0001')
insert into sanPham values ('SP0015',N'Nón SBTC Logo',189000,'D:\hinhAnh\sp0013.png',1000,N'Mo ta','CL0004','KD0005','KT0003','MS0003','XX0001','PL0003','NCC003','KM0001')
insert into sanPham values ('SP0016',N'Áo Khoác Unisex Teelab Skeleton Skateboarding AK051',550000,'D:\hinhAnh\TEELAB.png',1000,N'Mo ta','CL0005','KD0004','KT0002','MS0002','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0017',N'Áo Khoác Unisex Teelab Skeleton Skateboarding AK051',550000,'D:\hinhAnh\TEELAB.png',1000,N'Mo ta','CL0005','KD0004','KT0003','MS0002','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0018',N'Áo Khoác Unisex Teelab Skeleton Skateboarding AK051',550000,'D:\hinhAnh\TEELAB.png',1000,N'Mo ta','CL0005','KD0004','KT0004','MS0002','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0019',N'Quần Teelab Special Collection Premium Short',300000,'D:\hinhAnh\TEELAB_Short.png',1000,N'Mo ta','CL0002','KD0003','KT0002','MS0005','XX0001','PL0002','NCC003','KM0001')
insert into sanPham values ('SP0020',N'Quần Teelab Special Collection Premium Short',300000,'D:\hinhAnh\TEELAB_Short.png',1000,N'Mo ta','CL0002','KD0003','KT0003','MS0005','XX0001','PL0002','NCC003','KM0001')
insert into sanPham values ('SP0021',N'Quần Teelab Special Collection Premium Short',300000,'D:\hinhAnh\TEELAB_Short.png',1000,N'Mo ta','CL0002','KD0003','KT0004','MS0005','XX0001','PL0002','NCC003','KM0001')
insert into sanPham values ('SP0022',N'Quần Teelab Special Collection Premium Short',300000,'D:\hinhAnh\TEELAB_Short.png',1000,N'Mo ta','CL0002','KD0003','KT0005','MS0005','XX0001','PL0002','NCC003','KM0001')
insert into sanPham values ('SP0023',N'Áo Hoodie Teelab Punch Embroidery Logo Hoodie',550000,'D:\hinhAnh\TEELAB_HD.png',1000,N'Mo ta','CL0002','KD0002','KT0002','MS0001','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0024',N'Áo Hoodie Teelab Punch Embroidery Logo Hoodie',550000,'D:\hinhAnh\TEELAB_HD.png',1000,N'Mo ta','CL0002','KD0002','KT0003','MS0002','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0025',N'Áo Hoodie Teelab Punch Embroidery Logo Hoodie',550000,'D:\hinhAnh\TEELAB_HD.png',1000,N'Mo ta','CL0002','KD0002','KT0004','MS0003','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0026',N'Áo Hoodie Teelab Punch Embroidery Logo Hoodie',550000,'D:\hinhAnh\TEELAB_HD.png',1000,N'Mo ta','CL0002','KD0002','KT0005','MS0004','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0027',N'Mũ Lưỡi Trai Teelab Mini Logo Caps AC063',250000,'D:\hinhAnh\TEELAB_Non.png',1000,N'Mo ta','CL0001','KD0005','KT0002','MS0001','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0028',N'Mũ Lưỡi Trai Teelab Mini Logo Caps AC063',250000,'D:\hinhAnh\TEELAB_Non.png',1000,N'Mo ta','CL0001','KD0005','KT0002','MS0002','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0029',N'Mũ Lưỡi Trai Teelab Mini Logo Caps AC063',250000,'D:\hinhAnh\TEELAB_Non.png',1000,N'Mo ta','CL0001','KD0005','KT0002','MS0003','XX0001','PL0001','NCC003','KM0001')
insert into sanPham values ('SP0030',N'Mũ Lưỡi Trai Teelab Mini Logo Caps AC063',250000,'D:\hinhAnh\TEELAB_Non.png',1000,N'Mo ta','CL0001','KD0005','KT0002','MS0005','XX0001','PL0001','NCC003','KM0001')



----Them gia tri vao table nhan vien
insert into nhanvien values ('NV0001',N'Nguyễn Diệp Anh','1999-06-10',N'TP. Hồ Chí Minh','0924608193',1,25000000,'AnhNDmanag@gmail.com',N'Quản lý',null,null,0)
insert into nhanvien values ('NV0002',N'Trần Nam Anh','1989-09-11',N'TP. Hồ Chí Minh','0962342550',0,15000000,'namanh@gmail.com',N'Nhân Viên',null,null,0)
insert into nhanvien values ('NV0003',N'Nguyễn Thị Kim Dung','1999-06-14',N'TP. Hồ Chí Minh','0335337893',1,15000000,'kimdung06@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0004',N'Phạm Hồng Đăng','1998-05-13',N'TP. Hồ Chí Minh','0984476509',0,15000000,'DangPham123@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0005',N'Lê Minh Trí','1998-06-29',N'TP. Hồ Chí Minh','0163533782',0,15000000,'TriLe1998@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0006',N'Đỗ Quốc Hưng','2000-02-16',N'TP. Hồ Chí Minh','0327654298',0,12000000,'QHung@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0007',N'Vũ Quang Vinh','1988-08-28',N'TP. Hồ Chí Minh','0924655442',0,17000000,'QVhn@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0008',N'Đinh Quốc Trung','1999-06-26',N'TP. Hồ Chí Minh','0961728711',0,12000000,'trung990626@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0009',N'Lê Minh Tâm','1989-12-08',N'TP. Hồ Chí Minh','0969795020',0,17000000,'tamminhle@gmail.com',N'Nhân viên',null,null,0)
insert into nhanvien values ('NV0010',N'Trần Lê Nguyên','1998-08-22',N'TP. Hồ Chí Minh','0914529039',0,15000000,'nguyenclostaff@gmail.com',N'Nhân viên',null,null,0)


----Them gia tri vao table tai khoan
insert into TaiKhoan values ('admin','admin',1,'NV0001',0)
insert into TaiKhoan values ('TK0002','1111',0,'NV0002',0)
insert into TaiKhoan values ('TK0003','1111',0,'NV0003',0)
insert into TaiKhoan values ('TK0004','1111',0,'NV0004',0)
insert into TaiKhoan values ('TK0005','1111',0,'NV0005',0)
insert into TaiKhoan values ('TK0006','1111',0,'NV0006',0)
insert into TaiKhoan values ('TK0007','1111',0,'NV0007',0)
insert into TaiKhoan values ('TK0008','1111',0,'NV0008',0)
insert into TaiKhoan values ('TK0009','1111',0,'NV0009',0)
insert into TaiKhoan values ('TK0010','1111',0,'NV0010',0)


----Them gia tri vao table khach hang
insert into khachHang values ('KH0001',N'Lê Thị Hồng Vương', 'hvlt@gmail.com','0337098734',1)
insert into khachHang values ('KH0002',N'Bùi Ngọc Dương', 'duongngoc@gmail.com','0914712039',0)
insert into khachHang values ('KH0003',N'Dinh Huy Tường', 'tuong98@gmail.com','0961410277',0)
insert into khachHang values ('KH0004',N'Nguyễn Thị Khanh', 'khanhnt@gmail.com','0366357911',1)
insert into khachHang values ('KH0005',N'Nguyễn Văn An', 'nguyenvananhcm@gmail.com','0909393936',0)
insert into khachHang values ('KH0006',N'Đinh Văn Hoạt', 'vanhoathobo@gmail.com','0945721460',0)
insert into khachHang values ('KH0007',N'Lê Phan Như', 'nHuLe@gmail.com','0965351926',1)
insert into khachHang values ('KH0008',N'Trần Tuấn Linh', 'Linhtuan123@gmail.com','0986853638',0)
insert into khachHang values ('KH0009',N'Nguyễn Hoài Linh', 'linhhoainguyen2234@gmail.com','0366547796',0)
insert into khachHang values ('KH0010',N'Nguyễn Thanh Tùng', 'tungThanh898@gmail.com','0359188480',0)
insert into khachHang values ('KH0011',N'Phạm Thu Hà', 'THUHAAA@gmail.com','0909393939',1)
insert into khachHang values ('KH0012',N'Lê Quang Hải', 'quanghaigoal@gmail.com','0362625704',0)
insert into khachHang values ('KH0013',N'Nguyễn Lệ Quyên', 'QuyenLeHNCG@gmail.com','0968731191',1)
insert into khachHang values ('KH0014',N'Trần Thu Hiền', 'HienThumails@gmail.com','0961483232',1)
insert into khachHang values ('KH0015',N'Garcia Robert', 'RobertG@gmail.com','0337463968',0)
insert into khachHang values ('KH0016',N'Wilson Mary', 'Wilsonwilsonmary@gmail.com','0355811769',1)
insert into khachHang values ('KH0017',N'Martinez James', 'JamesMart99@gmail.com','0376920965',0)
insert into khachHang values ('KH0018',N'Mohamed Sena', 'SenaHam57@gmail.com','0384003368',1)
insert into khachHang values ('KH0019',N'Lopez Joseph', 'JosephLopeszz@gmail.com','0342827569',0)
insert into khachHang values ('KH0020',N'Miller Christopher', 'Christopher1996@gmail.com','0327759268',0)

----Them gia tri vao table hoa don
insert into HoaDon values ('HD0001','2022-06-15','NV0002','KH0001')
insert into HoaDon values ('HD0002','2022-06-18','NV0003','KH0002')
insert into HoaDon values ('HD0003','2022-07-19','NV0004','KH0003')
insert into HoaDon values ('HD0004','2022-07-21','NV0005','KH0004')
insert into HoaDon values ('HD0005','2022-07-25','NV0007','KH0005')
insert into HoaDon values ('HD0006','2022-07-28','NV0008','KH0006')
insert into HoaDon values ('HD0007','2022-08-9','NV0002','KH0007')
insert into HoaDon values ('HD0008','2022-08-10','NV0005','KH0008')
insert into HoaDon values ('HD0009','2022-08-15','NV0004','KH0009')
insert into HoaDon values ('HD0010','2022-09-18','NV0003','KH0010')
insert into HoaDon values ('HD0011','2022-09-20','NV0003','KH0011')
insert into HoaDon values ('HD0012','2022-09-25','NV0004','KH0012')
insert into HoaDon values ('HD0013','2022-09-29','NV0005','KH0013')
insert into HoaDon values ('HD0014','2022-10-26','NV0006','KH0014')
insert into HoaDon values ('HD0015','2022-10-26','NV0009','KH0015')
insert into HoaDon values ('HD0016','2022-10-27','NV0009','KH0016')
insert into HoaDon values ('HD0017','2022-10-28','NV0007','KH0017')
insert into HoaDon values ('HD0018','2022-10-29','NV0010','KH0018')
insert into HoaDon values ('HD0019','2022-10-30','NV0008','KH0019')
insert into HoaDon values ('HD0020','2022-10-30','NV0010','KH0020')


----Them gia tri vao table chi tiet hoa don
insert into ChiTietHoaDon values ('SP0001','HD0001',2,760000)
insert into ChiTietHoaDon values ('SP0004','HD0001',2,1300000)
insert into ChiTietHoaDon values ('SP0007','HD0001',2,760000)
insert into ChiTietHoaDon values ('SP0010','HD0001',2,360000)
insert into ChiTietHoaDon values ('SP0013','HD0001',2,378000)
insert into ChiTietHoaDon values ('SP0002','HD0002',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0002',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0002',2,760000)
insert into ChiTietHoaDon values ('SP0011','HD0002',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0002',2,378000)
insert into ChiTietHoaDon values ('SP0003','HD0003',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0003',2,1300000)
insert into ChiTietHoaDon values ('SP0009','HD0003',2,760000)
insert into ChiTietHoaDon values ('SP0012','HD0003',2,360000)
insert into ChiTietHoaDon values ('SP0015','HD0003',2,378000)
insert into ChiTietHoaDon values ('SP0001','HD0004',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0004',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0004',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0005',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0005',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0005',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0005',2,378000)

insert into ChiTietHoaDon values ('SP0001','HD0006',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0006',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0006',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0006',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0006',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0006',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0006',2,378000)

insert into ChiTietHoaDon values ('SP0001','HD0007',2,760000)
insert into ChiTietHoaDon values ('SP0004','HD0007',2,1300000)
insert into ChiTietHoaDon values ('SP0007','HD0007',2,760000)
insert into ChiTietHoaDon values ('SP0010','HD0007',2,360000)
insert into ChiTietHoaDon values ('SP0013','HD0007',2,378000)
insert into ChiTietHoaDon values ('SP0002','HD0008',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0008',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0008',2,760000)
insert into ChiTietHoaDon values ('SP0011','HD0008',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0008',2,378000)
insert into ChiTietHoaDon values ('SP0003','HD0009',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0009',2,1300000)
insert into ChiTietHoaDon values ('SP0009','HD0009',2,760000)
insert into ChiTietHoaDon values ('SP0012','HD0009',2,360000)
insert into ChiTietHoaDon values ('SP0015','HD0009',2,378000)
insert into ChiTietHoaDon values ('SP0001','HD0010',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0010',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0010',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0010',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0010',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0010',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0010',2,378000)

insert into ChiTietHoaDon values ('SP0001','HD0011',2,760000)
insert into ChiTietHoaDon values ('SP0004','HD0011',2,1300000)
insert into ChiTietHoaDon values ('SP0007','HD0011',2,760000)
insert into ChiTietHoaDon values ('SP0010','HD0011',2,360000)
insert into ChiTietHoaDon values ('SP0013','HD0011',2,378000)
insert into ChiTietHoaDon values ('SP0002','HD0012',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0012',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0012',2,760000)
insert into ChiTietHoaDon values ('SP0011','HD0012',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0012',2,378000)
insert into ChiTietHoaDon values ('SP0003','HD0012',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0013',2,1300000)
insert into ChiTietHoaDon values ('SP0009','HD0013',2,760000)
insert into ChiTietHoaDon values ('SP0012','HD0013',2,360000)
insert into ChiTietHoaDon values ('SP0015','HD0013',2,378000)
insert into ChiTietHoaDon values ('SP0001','HD0014',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0014',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0014',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0014',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0014',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0014',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0014',2,378000)


insert into ChiTietHoaDon values ('SP0001','HD0015',2,760000)
insert into ChiTietHoaDon values ('SP0004','HD0015',2,1300000)
insert into ChiTietHoaDon values ('SP0007','HD0015',2,760000)
insert into ChiTietHoaDon values ('SP0010','HD0015',2,360000)
insert into ChiTietHoaDon values ('SP0013','HD0015',2,378000)
insert into ChiTietHoaDon values ('SP0002','HD0016',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0016',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0016',2,760000)
insert into ChiTietHoaDon values ('SP0011','HD0016',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0016',2,378000)
insert into ChiTietHoaDon values ('SP0003','HD0017',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0017',2,1300000)
insert into ChiTietHoaDon values ('SP0009','HD0017',2,760000)
insert into ChiTietHoaDon values ('SP0012','HD0017',2,360000)
insert into ChiTietHoaDon values ('SP0015','HD0017',2,378000)
insert into ChiTietHoaDon values ('SP0001','HD0018',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0018',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0018',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0018',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0018',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0018',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0018',2,378000)
insert into ChiTietHoaDon values ('SP0003','HD0019',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0019',2,1300000)
insert into ChiTietHoaDon values ('SP0009','HD0019',2,760000)
insert into ChiTietHoaDon values ('SP0012','HD0019',2,360000)
insert into ChiTietHoaDon values ('SP0015','HD0019',2,378000)
insert into ChiTietHoaDon values ('SP0001','HD0020',2,760000)
insert into ChiTietHoaDon values ('SP0005','HD0020',2,1300000)
insert into ChiTietHoaDon values ('SP0008','HD0020',2,760000)
insert into ChiTietHoaDon values ('SP0002','HD0020',2,760000)
insert into ChiTietHoaDon values ('SP0006','HD0020',2,1300000)
insert into ChiTietHoaDon values ('SP0010','HD0020',2,360000)
insert into ChiTietHoaDon values ('SP0014','HD0020',2,378000)

--select
select * from [dbo].[nhacungcap]
select * from khuyenMai
select * from [dbo].sanPham
select * from [dbo].TaiKhoan
select * from [dbo].[chitiethoadon]
select * from [dbo].[hoadon]
select * from [dbo].[khachhang]
select * from [dbo].[nhanvien]
select * from XuatXu
select * from KieuDang
select * from KichThuoc
select * from MauSac
select * from ChatLieu
select * from PhanLoai
