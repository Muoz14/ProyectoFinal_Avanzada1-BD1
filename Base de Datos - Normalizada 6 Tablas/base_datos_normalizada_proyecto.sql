/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.32-MariaDB : Database - proyecto_final
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`proyecto_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `proyecto_final`;

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(50) NOT NULL,
  `apellido_cliente` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `borrado` varchar(1) NOT NULL DEFAULT '',
  `fecha_borrado` datetime DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `clientes` */

insert  into `clientes`(`id_cliente`,`nombre_cliente`,`apellido_cliente`,`telefono`,`correo`,`direccion`,`borrado`,`fecha_borrado`) values 
(1,'Marlon','Perez','55559999','jc_perez@gmail.com','Avenida Siempre Viva 742','',NULL),
(2,'Antonio','Borjas','96007914','aborjas244@gmail.com','B. La Encantadora','',NULL),
(3,'Karina','Ramos','99220714','ramoskari@gmail.com','B. Jilote','',NULL),
(4,'Rafael','Muñoz','99724682','rm10hn@gmail.com','Col. Villas del Sol','',NULL),
(5,'Daniel','Sagastume','99274241','sagasdani@gmail.com','B. La Libertad','',NULL);

/*Table structure for table `detalle_ventas` */

DROP TABLE IF EXISTS `detalle_ventas`;

CREATE TABLE `detalle_ventas` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `no_factura` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `no_factura` (`no_factura`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`no_factura`) REFERENCES `ventas` (`no_factura`),
  CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `chk_cantidad` CHECK (`cantidad` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `detalle_ventas` */

insert  into `detalle_ventas`(`id_detalle`,`no_factura`,`id_producto`,`cantidad`,`precio`) values 
(1,1,2,1,15000.00),
(2,2,2,1,15000.00),
(3,2,1,1,25000.00),
(4,3,4,1,1200.00),
(5,3,3,1,2100.00),
(6,4,1,1,25000.00),
(7,5,3,1,2100.00),
(8,5,4,1,1200.00),
(9,5,1,1,25000.00),
(10,6,2,1,15000.00),
(11,7,2,2,15000.00),
(12,8,1,1,25000.00),
(13,9,3,1,2100.00),
(14,10,1,3,25000.00);

/*Table structure for table `inventarios` */

DROP TABLE IF EXISTS `inventarios`;

CREATE TABLE `inventarios` (
  `id_movimiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `tipo_movimiento` varchar(20) NOT NULL,
  `cantidad` int(11) NOT NULL DEFAULT 0,
  `fecha_movimiento` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_movimiento`),
  KEY `fk_inventarios_productos` (`id_producto`),
  KEY `fk_invetnarios_usuarios` (`id_usuario`),
  CONSTRAINT `fk_inventarios_productos` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `fk_invetnarios_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `chk_cantidad` CHECK (`cantidad` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `inventarios` */

insert  into `inventarios`(`id_movimiento`,`id_producto`,`id_usuario`,`tipo_movimiento`,`cantidad`,`fecha_movimiento`) values 
(1,1,1,'Entrada',5,'2026-03-28 11:30:40'),
(2,1,2,'Salida',15,'2026-03-28 11:31:55'),
(3,2,1,'Entrada',2,'2026-03-30 08:22:19'),
(4,2,1,'Salida',1,'2026-03-30 09:05:08'),
(5,1,1,'Salida',1,'2026-03-30 09:05:08'),
(6,4,2,'Salida',1,'2026-03-30 09:09:47'),
(7,3,2,'Salida',1,'2026-03-30 09:09:47'),
(8,1,2,'Salida',1,'2026-03-30 09:46:48'),
(9,3,1,'Salida',1,'2026-03-30 09:49:48'),
(10,4,1,'Salida',1,'2026-03-30 09:49:48'),
(11,1,1,'Salida',1,'2026-03-30 09:49:48'),
(12,2,1,'Entrada',10,'2026-03-30 13:40:59'),
(13,2,1,'Salida',1,'2026-03-30 17:47:06'),
(14,1,2,'Entrada',8,'2026-03-30 18:39:44'),
(15,2,1,'Salida',2,'2026-03-30 18:53:57'),
(16,1,1,'Salida',1,'2026-04-01 18:08:28'),
(17,3,1,'Salida',1,'2026-04-01 21:08:27'),
(18,1,2,'Salida',3,'2026-04-01 21:09:15'),
(19,3,9,'Entrada',10,'2026-04-08 11:03:35'),
(20,4,11,'Entrada',3,'2026-04-08 11:03:48');

/*Table structure for table `productos` */

DROP TABLE IF EXISTS `productos`;

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(80) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `stock_actual` int(11) NOT NULL DEFAULT 0,
  `descripcion` varchar(100) DEFAULT NULL,
  `borrado` varchar(1) NOT NULL DEFAULT '',
  `fecha_borrado` datetime DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  CONSTRAINT `chk_stock` CHECK (`stock_actual` >= 0),
  CONSTRAINT `chk_precio` CHECK (`precio_unitario` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `productos` */

insert  into `productos`(`id_producto`,`nombre_producto`,`precio_unitario`,`stock_actual`,`descripcion`,`borrado`,`fecha_borrado`) values 
(1,'Laptop Dell XPS',25000.00,6,'Laptop de gama alta','',NULL),
(2,'Samsung Galaxy S20',15000.00,10,'Celular','',NULL),
(3,'Teclado',2100.00,11,'Teclado Logitech','',NULL),
(4,'Mouse',1200.00,4,'Mouse Logitech','','2026-04-08 00:27:46'),
(5,'IPhone 14 Pro Max',19000.00,5,'Celular Apple Gama Premium','',NULL);

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_empleado` varchar(50) NOT NULL,
  `apellido_empleado` varchar(50) NOT NULL,
  `user` varchar(25) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `borrado` varchar(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id_usuario`,`nombre_empleado`,`apellido_empleado`,`user`,`pass`,`borrado`) values 
(1,'Angel','Muñoz','Admin','1234',''),
(2,'Ever','Chavez','echavez','1234',''),
(8,'Ever','Chavez','echavez','1234','*'),
(9,'Jeymi','Chavez','mimibear','Majano2005',''),
(10,'Shelsi','Enamorado','yanicastx','2005',''),
(11,'Jorge','Orellana','Hamie','2005','');

/*Table structure for table `ventas` */

DROP TABLE IF EXISTS `ventas`;

CREATE TABLE `ventas` (
  `no_factura` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT current_timestamp(),
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`no_factura`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `ventas` */

insert  into `ventas`(`no_factura`,`fecha`,`id_cliente`,`id_usuario`) values 
(1,'2026-03-30 08:58:50',1,1),
(2,'2026-03-30 09:05:08',1,1),
(3,'2026-03-30 09:09:47',2,2),
(4,'2026-03-30 09:46:48',2,2),
(5,'2026-03-30 09:49:48',1,1),
(6,'2026-03-30 17:47:06',1,1),
(7,'2026-03-30 18:53:57',2,1),
(8,'2026-04-01 18:08:28',1,1),
(9,'2026-04-01 21:08:27',2,1),
(10,'2026-04-01 21:09:15',2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
