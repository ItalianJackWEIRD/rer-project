insert into host(name, surname) values('Gino', 'Forti');
insert into host(name, surname) values('Sandro', 'Reni');
insert into host(name, surname) values('Claudio', 'Campioni');
insert into host(name, surname) values('Achille', 'Lauro');


insert into struttura (name, city, host_id) values('West Hollywood Oasis', 'Los Angeles', '1');
insert into struttura (name, city, host_id) values('Bosco Verticale', 'Milano', '2');
insert into struttura (name, city, host_id) values('Villa Marta', 'Roma', '2');
insert into struttura (name, city, host_id) values('Villa Este', 'Tivoli', '2');
insert into struttura (name, city, host_id) values('Casa Bianca', 'Washington', '3');
insert into struttura (name, city, host_id) values('A Casa De Sandro', 'Roma', '4');


/*immagini hosts*/
INSERT INTO immagine (file_name, image_data) VALUES ('hostGinoForti', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\hostGinoForti.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('hostSandroReni', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\hostSandroReni.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('hostClaudioCampioni', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\hostClaudioCampioni.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('hostAchilleLauro', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\hostAchilleLauro.png'));


/*immagini strutture*/
INSERT INTO immagine (file_name, image_data) VALUES ('west-hollywood-oasis', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\west-hollywood-oasis.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('bosco-verticale-milano', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\bosco-verticale-milano.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('villa-marta', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\villa-marta.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('villa-deste', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\villa-deste.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('casa-bianca', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\casa-bianca.png'));
INSERT INTO immagine (file_name, image_data) VALUES ('casa-de-sandro', pg_read_binary_file('C:\Users\01fge\Documents\GitHub\rer-project\src\main\resources\static\images\casa-de-sandro.png'));






