-- BASE DE DATOS
CREATE DATABASE IF NOT EXISTS censo_inegi;
USE censo_inegi;

-- CATÁLOGOS
CREATE TABLE municipios (
    id_municipio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE localidades (
    id_localidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    id_municipio INT,
    FOREIGN KEY (id_municipio) REFERENCES municipios(id_municipio)
);

CREATE TABLE tipoviviendas (
    id_tipovivienda INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE escolaridades (
    id_escolaridad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE ocupaciones (
    id_ocupacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100)
);

CREATE TABLE sexos (
    id_sexo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20)
);

CREATE TABLE actividadeseconomicas (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion VARCHAR(255)
);

-- TABLA VIVIENDAS
CREATE TABLE viviendas (
    id_vivienda INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(100),
    num_exterior VARCHAR(10),
    num_interior VARCHAR(10),
    colonia VARCHAR(100),
    codigo_postal VARCHAR(10),
    id_localidad INT,
    id_municipio INT,
    id_tipo_vivienda INT,
    num_cuartos INT,
    num_banios INT,
    material_predominante VARCHAR(100),
    servicios_basicos VARCHAR(255),
    observaciones TEXT,
    estatus BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (id_localidad) REFERENCES localidades(id_localidad),
    FOREIGN KEY (id_municipio) REFERENCES municipios(id_municipio),
    FOREIGN KEY (id_tipo_vivienda) REFERENCES tipoviviendas(id_tipovivienda)
);

-- TABLA HABITANTES
CREATE TABLE habitantes (
    id_habitante INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    paterno VARCHAR(100),
    materno VARCHAR(100),
    edad INT,
    fecha_nac DATE,
    curp VARCHAR(18) UNIQUE,
    id_sexo INT,
    id_vivienda INT,
    id_escolaridad INT,
    id_ocupacion INT,
    estatus BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (id_sexo) REFERENCES sexos(id_sexo),
    FOREIGN KEY (id_vivienda) REFERENCES viviendas(id_vivienda),
    FOREIGN KEY (id_escolaridad) REFERENCES escolaridades(id_escolaridad),
    FOREIGN KEY (id_ocupacion) REFERENCES ocupaciones(id_ocupacion)
);

-- RELACIÓN MUCHOS A MUCHOS
CREATE TABLE vivienda_actividad (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vivienda INT,
    id_actividad INT,

    FOREIGN KEY (id_vivienda) REFERENCES viviendas(id_vivienda),
    FOREIGN KEY (id_actividad) REFERENCES actividadeseconomicas(id_actividad)
);

-- USUARIOS
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    nombre VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);