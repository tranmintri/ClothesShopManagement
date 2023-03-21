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

alter table ChiTietHoaDon
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

alter table ChiTietHoaDon
add constraint FK_cthd_hd foreign key(maHoaDon) references hoadon(maHoaDon)

alter table ChiTietHoaDon
add constraint FK_cthd_sp foreign key(maSP) references sanPham(maSP)

ALTER TABLE nhanvien
ADD DEFAULT(null) FOR OTP

ALTER TABLE nhanvien
ADD DEFAULT(null) FOR expriedAt

ALTER TABLE nhanvien
ADD DEFAULT(0) FOR isDeleted

ALTER TABLE TaiKhoan
ADD DEFAULT(0) FOR isDeleted

--update nhanvien set email = 'tranminhtri9a1@gmail.com' where maNhanVien = 'NV0001'
