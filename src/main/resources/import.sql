insert into host(name, surname) values('John', 'Cena');
insert into host(name, surname) values('Lorenzo', 'Pellegrini');
insert into host(name, surname) values('John Fitzgerald', 'Kennedy');
insert into struttura (name, city, host_id) values('Bosco Verticale', 'Milano', '2');
insert into struttura (name, city, host_id) values('Villa Marta', 'Roma', '2');
insert into struttura (name, city, host_id) values('Villa Este', 'Tivoli', '2');
insert into struttura (name, city, host_id) values('West Hollywood Oasis', 'Los Angeles', '1');
insert into struttura (name, city, host_id) values('Casa Bianca', 'Washington', '3');

/*immagini hosts*/
insert into immagine(file_name, image_data) values ('hostJohnCena', LOAD_FILE('images\hostJohnCena.png'));
insert into immagine(file_name, image_data) values ('hostJohnFitzgeraldKennedy', LOAD_FILE('images\hostJohnFitzgeraldKennedy.png'));
insert into immagine(file_name, image_data) values ('hostLorenzoPellegrini', LOAD_FILE('images\hostLorenzoPellegrini.png'));
insert into immagine(file_name, image_data) values ('hostAchilleLauro', LOAD_FILE('images\hostAchilleLauro.png'));


/*immagini strutture*/
insert into immagine(file_name, image_data) values ('villa-marta', LOAD_FILE('images\villa-marta.png'));
insert into immagine(file_name, image_data) values ('villa-deste', LOAD_FILE('images\villa-deste.png'));
