-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2023 at 03:32 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rpll_travel`
--

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` int(11) NOT NULL,
  `nama_hotel` varchar(25) NOT NULL,
  `alamat_hotel` varchar(35) NOT NULL,
  `deskripsi` text NOT NULL,
  `rating` int(1) NOT NULL DEFAULT 5,
  `promo` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `nama_hotel`, `alamat_hotel`, `deskripsi`, `rating`, `promo`) VALUES
(1, 'Hotel Mawar', 'Jl Mawar No 11', 'Lorem', 5, 0),
(2, 'Hotel Tulip', 'Jl Tulip No 22', 'Ipsum', 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `kamar_hotel`
--

CREATE TABLE `kamar_hotel` (
  `nomor_kamar` varchar(4) NOT NULL,
  `tipe_kamar` enum('Single','Double','Deluxe','Suite') NOT NULL,
  `harga_kamar` double NOT NULL,
  `status_kamar` enum('Terisi','Kosong') NOT NULL DEFAULT 'Kosong',
  `id_hotel` int(11) NOT NULL,
  `id_tiket_hotel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kamar_hotel`
--

INSERT INTO `kamar_hotel` (`nomor_kamar`, `tipe_kamar`, `harga_kamar`, `status_kamar`, `id_hotel`, `id_tiket_hotel`) VALUES
('1201', 'Single', 500000, 'Terisi', 2, 2),
('1302', 'Double', 750000, 'Kosong', 2, NULL),
('1403', 'Deluxe', 900000, 'Kosong', 2, NULL),
('1504', 'Suite', 1200000, 'Kosong', 2, NULL),
('201', 'Single', 200000, 'Terisi', 1, 1),
('302', 'Double', 300000, 'Kosong', 1, NULL),
('403', 'Deluxe', 500000, 'Kosong', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `kursi_pesawat`
--

CREATE TABLE `kursi_pesawat` (
  `nomor_kursi` varchar(4) NOT NULL,
  `tipe_kursi` enum('First','Business','Economy') NOT NULL,
  `harga_kursi` double NOT NULL,
  `status_kursi` enum('Terisi','Kosong') NOT NULL DEFAULT 'Kosong',
  `id_pesawat` int(11) NOT NULL,
  `id_tiket_pesawat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kursi_pesawat`
--

INSERT INTO `kursi_pesawat` (`nomor_kursi`, `tipe_kursi`, `harga_kursi`, `status_kursi`, `id_pesawat`, `id_tiket_pesawat`) VALUES
('11A', 'Business', 300000, 'Kosong', 1, NULL),
('11B', 'Business', 350000, 'Terisi', 2, 1),
('11C', 'Business', 400000, 'Kosong', 3, NULL),
('1A', 'First', 700000, 'Kosong', 1, NULL),
('1B', 'First', 750000, 'Kosong', 2, NULL),
('1C', 'First', 800000, 'Terisi', 3, 2),
('21A', 'Economy', 100000, 'Kosong', 1, NULL),
('21B', 'Economy', 150000, 'Kosong', 2, NULL),
('21C', 'Economy', 200000, 'Kosong', 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `nomor_telepon` varchar(13) NOT NULL,
  `alamat` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama`, `email`, `password`, `jenis_kelamin`, `tanggal_lahir`, `nomor_telepon`, `alamat`) VALUES
(1, 'Agung', 'agung@mail.com', 'agung', 'Pria', '2001-01-01', '08123456781', 'Jl ABC No 1'),
(2, 'Bagung', 'bagung@mail.com', 'bagung', 'Pria', '2002-02-02', '08123456782', 'Jl BCD No 2'),
(3, 'Cagung', 'cagung@mail.com', 'cagung', 'Pria', '2003-03-03', '08123456783', 'Jl CDE No 3'),
(4, 'Dagung', 'dagung@mail.com', 'dagung', 'Pria', '2004-04-04', '08123456784', 'Jl DEF No 4');

-- --------------------------------------------------------

--
-- Table structure for table `pesawat`
--

CREATE TABLE `pesawat` (
  `id_pesawat` int(11) NOT NULL,
  `maskapai` varchar(25) NOT NULL,
  `tempat_berangkat` varchar(25) NOT NULL,
  `tujuan_berangkat` varchar(25) NOT NULL,
  `tanggal_berangkat` date NOT NULL,
  `jam_berangkat` time NOT NULL,
  `promo` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesawat`
--

INSERT INTO `pesawat` (`id_pesawat`, `maskapai`, `tempat_berangkat`, `tujuan_berangkat`, `tanggal_berangkat`, `jam_berangkat`, `promo`) VALUES
(1, 'KalongAir', 'Bandung', 'Surabaya', '2023-03-29', '07:00:00', 0),
(2, 'CodotAir', 'Surabaya', 'Bali', '2023-03-30', '08:00:00', 0),
(3, 'KampretAir', 'Bali', 'Jakarta', '2023-03-31', '09:00:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tiket_hotel`
--

CREATE TABLE `tiket_hotel` (
  `id_tiket_hotel` int(11) NOT NULL,
  `tanggal_pemesanan` date NOT NULL,
  `status_pemesanan` enum('Selesai','Dikembalikan','Sedang Berjalan') NOT NULL DEFAULT 'Sedang Berjalan',
  `id_pengguna` int(11) NOT NULL,
  `id_voucher` int(11) DEFAULT NULL,
  `nama_penginap` varchar(25) NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `tanggal_inap` date NOT NULL,
  `lama_inap` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tiket_hotel`
--

INSERT INTO `tiket_hotel` (`id_tiket_hotel`, `tanggal_pemesanan`, `status_pemesanan`, `id_pengguna`, `id_voucher`, `nama_penginap`, `jenis_kelamin`, `tanggal_lahir`, `tanggal_inap`, `lama_inap`) VALUES
(1, '2023-03-29', 'Sedang Berjalan', 1, NULL, 'Rendi', 'Pria', '2001-01-02', '2023-03-30', 1),
(2, '2023-03-29', 'Sedang Berjalan', 2, NULL, 'Bagung', 'Pria', '2002-02-02', '2023-03-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tiket_pesawat`
--

CREATE TABLE `tiket_pesawat` (
  `id_tiket_pesawat` int(11) NOT NULL,
  `tanggal_pemesanan` date NOT NULL,
  `status_pemesanan` enum('Selesai','Dikembalikan','Sedang Berjalan') NOT NULL DEFAULT 'Sedang Berjalan',
  `id_pengguna` int(11) NOT NULL,
  `id_voucher` int(11) DEFAULT NULL,
  `nama_depan` varchar(25) NOT NULL,
  `nama_belakang` varchar(25) NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `email` varchar(25) NOT NULL,
  `nomor_telepon` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tiket_pesawat`
--

INSERT INTO `tiket_pesawat` (`id_tiket_pesawat`, `tanggal_pemesanan`, `status_pemesanan`, `id_pengguna`, `id_voucher`, `nama_depan`, `nama_belakang`, `jenis_kelamin`, `tanggal_lahir`, `email`, `nomor_telepon`) VALUES
(1, '2023-03-29', 'Sedang Berjalan', 3, NULL, 'Cagung', 'Cagung', 'Pria', '2003-03-03', 'cagung@mail.com', '08123456783'),
(2, '2023-03-29', 'Sedang Berjalan', 4, NULL, 'Steven', 'Sungliman', 'Pria', '2002-10-02', 'steven@mail.com', '08100000001');

-- --------------------------------------------------------

--
-- Table structure for table `voucher`
--

CREATE TABLE `voucher` (
  `id_voucher` int(11) NOT NULL,
  `nama_voucher` varchar(35) NOT NULL,
  `nilai` double NOT NULL,
  `tipe_tiket` enum('Pesawat','Hotel') NOT NULL,
  `status_penggunaan` enum('Berlaku','Tidak Berlaku') NOT NULL DEFAULT 'Berlaku',
  `id_pengguna` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`);

--
-- Indexes for table `kamar_hotel`
--
ALTER TABLE `kamar_hotel`
  ADD PRIMARY KEY (`nomor_kamar`),
  ADD KEY `id_hotel` (`id_hotel`),
  ADD KEY `id_tiket_hotel` (`id_tiket_hotel`);

--
-- Indexes for table `kursi_pesawat`
--
ALTER TABLE `kursi_pesawat`
  ADD PRIMARY KEY (`nomor_kursi`),
  ADD KEY `id_pesawat` (`id_pesawat`),
  ADD KEY `id_tiket_pesawat` (`id_tiket_pesawat`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `pesawat`
--
ALTER TABLE `pesawat`
  ADD PRIMARY KEY (`id_pesawat`);

--
-- Indexes for table `tiket_hotel`
--
ALTER TABLE `tiket_hotel`
  ADD PRIMARY KEY (`id_tiket_hotel`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `id_voucher` (`id_voucher`);

--
-- Indexes for table `tiket_pesawat`
--
ALTER TABLE `tiket_pesawat`
  ADD PRIMARY KEY (`id_tiket_pesawat`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `id_voucher` (`id_voucher`);

--
-- Indexes for table `voucher`
--
ALTER TABLE `voucher`
  ADD PRIMARY KEY (`id_voucher`),
  ADD KEY `id_pengguna` (`id_pengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pesawat`
--
ALTER TABLE `pesawat`
  MODIFY `id_pesawat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tiket_hotel`
--
ALTER TABLE `tiket_hotel`
  MODIFY `id_tiket_hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tiket_pesawat`
--
ALTER TABLE `tiket_pesawat`
  MODIFY `id_tiket_pesawat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `voucher`
--
ALTER TABLE `voucher`
  MODIFY `id_voucher` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `kamar_hotel`
--
ALTER TABLE `kamar_hotel`
  ADD CONSTRAINT `kamar_hotel_ibfk_1` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `kamar_hotel_ibfk_2` FOREIGN KEY (`id_tiket_hotel`) REFERENCES `tiket_hotel` (`id_tiket_hotel`);

--
-- Constraints for table `kursi_pesawat`
--
ALTER TABLE `kursi_pesawat`
  ADD CONSTRAINT `kursi_pesawat_ibfk_1` FOREIGN KEY (`id_pesawat`) REFERENCES `pesawat` (`id_pesawat`),
  ADD CONSTRAINT `kursi_pesawat_ibfk_2` FOREIGN KEY (`id_tiket_pesawat`) REFERENCES `tiket_pesawat` (`id_tiket_pesawat`);

--
-- Constraints for table `tiket_hotel`
--
ALTER TABLE `tiket_hotel`
  ADD CONSTRAINT `tiket_hotel_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `tiket_hotel_ibfk_2` FOREIGN KEY (`id_voucher`) REFERENCES `voucher` (`id_voucher`);

--
-- Constraints for table `tiket_pesawat`
--
ALTER TABLE `tiket_pesawat`
  ADD CONSTRAINT `tiket_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`),
  ADD CONSTRAINT `tiket_ibfk_2` FOREIGN KEY (`id_voucher`) REFERENCES `voucher` (`id_voucher`);

--
-- Constraints for table `voucher`
--
ALTER TABLE `voucher`
  ADD CONSTRAINT `voucher_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
