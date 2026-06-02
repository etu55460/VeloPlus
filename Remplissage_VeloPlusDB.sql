INSERT INTO category (label, description) VALUES
('Standard', 'Client particulier ou professionnel sans condition speciale'),
('Fidele', 'Client revenant regulierement au magasin'),
('Premium', 'Client important avec suivi commercial prioritaire'),
('Surveillance paiement', 'Client avec facture ou acompte a surveiller');

INSERT INTO size (label) VALUES
('XS'),
('S'),
('M'),
('L'),
('XL');

INSERT INTO status (label) VALUES
('Nouveau'),
('En attente'),
('Accepte'),
('En cours'),
('Pret'),
('Termine'),
('Annule');

INSERT INTO client_kind (label) VALUES
('Particulier'),
('Professionnel');

INSERT INTO color (color_name) VALUES
('Noir'),
('Blanc'),
('Rouge'),
('Bleu'),
('Vert'),
('Gris');

INSERT INTO payment_method (payment_method_name) VALUES
('Carte bancaire'),
('Especes'),
('Virement'),
('Bancontact'),
('PayPal');

INSERT INTO supplier (supplier_name, phone, email, address) VALUES
('BikeParts Europe', '081100001', 'contact@bikeparts.eu', 'Rue des Ateliers 12, 5000 Namur'),
('CyclePro Distribution', '081100002', 'info@cyclepro.be', 'Avenue du Commerce 5, 6000 Charleroi'),
('UrbanBike Supply', '081100003', 'sales@urbanbike.be', 'Rue du Parc 8, 1000 Bruxelles'),
('Velotech Benelux', '081100004', 'contact@velotech.be', 'Zone Industrielle 4, 4000 Liege'),
('Atelier Roue Libre', '081100005', 'hello@rouelibre.be', 'Rue du Depot 21, 5000 Namur'),
('Nordic Cycle Parts', '081100006', 'support@nordiccycle.be', 'Boulevard Industriel 7, 7000 Mons');

INSERT INTO client (first_name, last_name, phone, email, address, kind_id, category_id) VALUES
('Lucas', 'Martin', '0470000001', 'lucas.martin@gmail.be', 'Rue de Fer 10, 5000 Namur', 1, 2),
('Emma', 'Dubois', '0470000002', 'emma.dubois@gmail.be', 'Avenue Reine Astrid 22, 5000 Namur', 1, 1),
('Noah', 'Lambert', '0470000003', 'noah.lambert@gmail.be', 'Rue Haute 15, 5100 Jambes', 1, 3),
('Sophie', 'Leroy', '0470000004', 'sophie.leroy@gmail.be', 'Rue de Bruxelles 7, 5030 Gembloux', 1, 1),
('Maxime', 'Petit', '0470000005', 'maxime.petit@gmail.be', 'Rue Saint-Nicolas 3, 5000 Namur', 1, 4),
('Green Delivery', 'SRL', '081200001', 'contact@greendelivery.be', 'Parc d activite 9, 5000 Namur', 2, 1),
('Sport Center Namur', 'SA', '081200002', 'info@sportcenter.be', 'Chaussee de Louvain 55, 5004 Bouge', 2, 3),
('Julie', 'Moreau', '0470000006', 'julie.moreau@mail.be', 'Rue des Carmes 18, 5000 Namur', 1, 2),
('Thomas', 'Renard', '0470000007', 'thomas.renard@mail.be', 'Rue du Bord de Meuse 4, 5000 Namur', 1, 1),
('Camille', 'Henry', '0470000008', 'camille.henry@mail.be', 'Rue des Brasseurs 19, 5500 Dinant', 1, 2),
('Atelier Velo Campus', 'ASBL', '081200003', 'atelier@velocampus.be', 'Rue de l Universite 12, 5000 Namur', 2, 2),
('Livraison Express Wallonie', 'SRL', '081200004', 'ops@lexwallonie.be', 'Parc logistique 18, 5020 Suarlee', 2, 4),
('Nina', 'Lemaire', '0470000009', 'nina.lemaire@mail.be', 'Rue des Tilleuls 8, 5300 Andenne', 1, 3),
('Yanis', 'Michel', '0470000010', 'yanis.michel@mail.be', 'Rue de la Gare 41, 5590 Ciney', 1, 1),
('Maison du Cycle', 'SRL', '081200005', 'contact@maisoncycle.be', 'Zone artisanale 6, 5030 Gembloux', 2, 3),
('Laura', 'Baudry', '0470000011', 'laura.baudry@mail.be', 'Rue du Centre 27, 5310 Eghezee', 1, 2);

INSERT INTO bike (model, size_id, color_id, unit_price, is_used, condition_note, purchase_price, description) VALUES
('Trek Marlin 5', 3, 1, 649.99, FALSE, NULL, NULL, 'VTT polyvalent pour loisirs et trajets quotidiens'),
('Giant Escape 3', 4, 4, 549.99, FALSE, NULL, NULL, 'Velo fitness urbain pour deplacements quotidiens'),
('Cube Aim Pro', 3, 6, 699.99, FALSE, NULL, NULL, 'VTT semi-rigide pour chemins et forets'),
('Specialized Sirrus 2.0', 2, 2, 599.99, FALSE, NULL, NULL, 'Velo urbain leger avec position sportive'),
('Btwin Riverside 500', 3, 5, 299.99, TRUE, 'Bon etat general, revision effectuee', 140.00, 'Velo trekking occasion revise'),
('Scott Aspect 950', 4, 3, 449.99, TRUE, 'Quelques griffes sur le cadre', 220.00, 'VTT occasion pret a rouler'),
('Cannondale Quick 4', 2, 1, 799.99, FALSE, NULL, NULL, 'Velo urbain leger avec freins a disque'),
('Orbea Alma H30', 5, 6, 999.99, FALSE, NULL, NULL, 'VTT sportif pour pratique reguliere'),
('Merida Crossway 20', 3, 2, 629.99, FALSE, NULL, NULL, 'Velo trekking mixte confortable'),
('Lapierre Edge 5.7', 4, 1, 749.99, FALSE, NULL, NULL, 'VTT loisir polyvalent avec transmission fiable'),
('Gazelle CityGo', 2, 4, 899.99, TRUE, 'Cadre tres propre, batterie a verifier', 430.00, 'Velo urbain electrique occasion'),
('BMC Alpenchallenge', 3, 6, 1199.99, FALSE, NULL, NULL, 'Velo urbain premium pour usage intensif'),
('Cube Reaction Pro', 5, 3, 1349.99, TRUE, 'Usure legere de la transmission', 650.00, 'VTT occasion haut de gamme'),
('Scott Sub Cross 40', 4, 5, 579.99, FALSE, NULL, NULL, 'Velo trekking confortable pour longues sorties');

INSERT INTO part (reference, part_name, unit_price, stock_quantity, supplier_id) VALUES
(1001, 'Chambre a air 29 pouces', 7.99, 60, 1),
(1002, 'Pneu route 700x25', 24.99, 25, 1),
(1003, 'Patins de frein V-Brake', 12.50, 40, 2),
(1004, 'Cable de frein inox', 5.99, 80, 2),
(1005, 'Casque urbain taille M', 49.99, 15, 3),
(1006, 'Antivol U renforce', 34.99, 20, 3),
(1007, 'Chaine 10 vitesses', 19.99, 30, 4),
(1008, 'Eclairage LED avant et arriere', 29.99, 35, 4),
(1009, 'Selle confort gel', 39.99, 12, 1),
(1010, 'Pompe a main compacte', 14.99, 50, 2),
(1011, 'Pedales plates aluminium', 24.50, 18, 5),
(1012, 'Derailleur arriere 11 vitesses', 59.90, 9, 6),
(1013, 'Cassette 11-34', 44.90, 14, 6),
(1014, 'Garde-boue trekking', 27.50, 22, 5),
(1015, 'Porte-bagages arriere', 39.00, 16, 5),
(1016, 'Plaquettes de frein disque', 18.75, 28, 6),
(1017, 'Batterie eclairage USB', 16.20, 24, 5),
(1018, 'Bequille laterale reglable', 14.40, 19, 5);

INSERT INTO sale_order (client_id, order_date, status_id, deposit_amount, is_professional_order, notes) VALUES
(1, '2026-01-12', 6, 100.00, FALSE, 'Velo et antivol retires en magasin'),
(2, '2026-01-20', 4, 50.00, FALSE, 'Preparation du velo et du casque en cours'),
(6, '2026-02-05', 3, 300.00, TRUE, 'Commande professionnelle pour trois velos urbains'),
(3, '2026-02-18', 2, NULL, FALSE, 'Attente confirmation client pour velo occasion'),
(5, '2026-03-03', 6, 500.00, TRUE, 'Deux velos urbains et accessoires factures'),
(1, '2026-03-14', 1, NULL, FALSE, 'Nouvelle commande de pieces pour entretien'),
(4, '2026-04-02', 7, NULL, FALSE, 'Commande annulee par le client'),
(5, '2026-04-11', 5, 80.00, FALSE, 'Commande prete pour retrait en magasin'),
(9, '2026-04-18', 6, 120.00, FALSE, 'Velo trekking et accessoires retires en magasin'),
(10, '2026-04-22', 3, 250.00, FALSE, 'Commande velo neuf avec options de securite'),
(11, '2026-04-25', 4, NULL, TRUE, 'Commande de pieces pour atelier campus'),
(12, '2026-05-02', 2, 400.00, TRUE, 'Commande flotte en attente de validation finale'),
(13, '2026-05-05', 5, 0.00, FALSE, 'Velo occasion prepare pour retrait'),
(14, '2026-05-11', 1, NULL, FALSE, 'Demande recente de pieces de transmission'),
(15, '2026-05-13', 6, 600.00, TRUE, 'Commande entreprise livree et facturee'),
(16, '2026-05-16', 7, 50.00, FALSE, 'Client a annule avant retrait');

INSERT INTO sale_order_line (order_id, order_line_id, bike_id, part_id, quantity, unit_price, discount_percent) VALUES
(1, 1, 1, NULL, 1, 649.99, 5.00),
(1, 2, NULL, 6, 1, 34.99, NULL),
(2, 1, 2, NULL, 1, 549.99, NULL),
(2, 2, NULL, 5, 1, 49.99, 10.00),
(3, 1, 4, NULL, 3, 599.99, 8.00),
(3, 2, NULL, 8, 3, 29.99, NULL),
(4, 1, 5, NULL, 1, 299.99, NULL),
(5, 1, 7, NULL, 2, 799.99, 7.50),
(5, 2, NULL, 10, 2, 14.99, NULL),
(6, 1, NULL, 3, 2, 12.50, NULL),
(6, 2, NULL, 4, 2, 5.99, NULL),
(7, 1, 6, NULL, 1, 449.99, NULL),
(8, 1, 3, NULL, 1, 699.99, 5.00),
(8, 2, NULL, 1, 2, 7.99, NULL),
(9, 1, 9, NULL, 1, 629.99, 3.00),
(9, 2, NULL, 14, 1, 27.50, NULL),
(9, 3, NULL, 15, 1, 39.00, 5.00),
(10, 1, 10, NULL, 1, 749.99, NULL),
(10, 2, NULL, 11, 1, 24.50, NULL),
(10, 3, NULL, 6, 1, 34.99, 10.00),
(11, 1, NULL, 3, 10, 12.50, 12.00),
(11, 2, NULL, 4, 10, 5.99, NULL),
(11, 3, NULL, 16, 12, 18.75, 8.00),
(12, 1, 12, NULL, 2, 1199.99, 9.00),
(12, 2, NULL, 15, 2, 39.00, NULL),
(12, 3, NULL, 18, 2, 14.40, NULL),
(13, 1, 11, NULL, 1, 899.99, 15.00),
(13, 2, NULL, 17, 1, 16.20, NULL),
(14, 1, NULL, 1, 4, 7.99, NULL),
(14, 2, NULL, 13, 1, 44.90, NULL),
(14, 3, NULL, 12, 1, 59.90, 5.00),
(15, 1, 14, NULL, 3, 579.99, 6.50),
(15, 2, NULL, 8, 3, 29.99, NULL),
(15, 3, NULL, 14, 3, 27.50, 10.00),
(16, 1, 13, NULL, 1, 1349.99, NULL),
(16, 2, NULL, 16, 2, 18.75, NULL);

INSERT INTO repair_order (client_id, problem_description, deposit_date, appointment_date, estimate_amount, accepted_estimate, labor_hours, status_id, notes) VALUES
(1, 'Crevaison roue arriere sur velo de ville', '2026-01-10', '2026-01-11', 25.00, TRUE, 0.50, 6, 'Chambre a air remplacee et pression controlee'),
(2, 'Freins qui grincent et manque de puissance', '2026-01-21', '2026-01-23', 45.00, TRUE, 1.00, 4, 'Reglage des freins en cours'),
(3, 'Revision complete avant reprise de saison', '2026-02-01', '2026-02-04', 85.00, TRUE, 2.00, 5, 'Velo pret a recuperer'),
(4, 'Transmission qui saute sur plusieurs pignons', '2026-02-14', '2026-02-17', 65.00, FALSE, NULL, 2, 'Attente acceptation du devis'),
(5, 'Remplacement chaine usee', '2026-03-02', '2026-03-03', 39.99, TRUE, 0.75, 3, 'Piece commandee et facture a payer'),
(8, 'Eclairage defectueux sur velo urbain', '2026-03-18', '2026-03-20', 29.99, TRUE, 0.50, 6, 'Eclairage remplace et teste'),
(6, 'Entretien de flotte de velos de livraison', '2026-04-05', '2026-04-10', 250.00, TRUE, 6.00, 4, 'Intervention professionnelle en cours'),
(7, 'Controle securite velo client', '2026-04-12', NULL, 0.00, FALSE, NULL, 1, 'Depot recent sans rendez-vous'),
(9, 'Pneu avant use et voile leger de la roue', '2026-04-19', '2026-04-20', 58.00, TRUE, 1.25, 6, 'Pneu remplace et roue devoilee'),
(10, 'Revision complete avec controle freins et transmission', '2026-04-24', '2026-04-27', 95.00, TRUE, 2.50, 4, 'Entretien en cours'),
(11, 'Entretien de velos d atelier pour mise a disposition des etudiants', '2026-04-26', '2026-04-30', 310.00, TRUE, 7.50, 3, 'Intervention professionnelle planifiee'),
(12, 'Remplacement freins sur plusieurs velos de livraison', '2026-05-03', '2026-05-06', 420.00, FALSE, NULL, 2, 'Devis envoye au responsable logistique'),
(13, 'Bruit au pedalier et jeu dans les manivelles', '2026-05-06', NULL, 70.00, FALSE, NULL, 1, 'Depot sans rendez-vous'),
(14, 'Montage accessoires apres achat de pieces', '2026-05-12', '2026-05-13', 35.00, TRUE, 0.75, 5, 'Accessoires montes, retrait possible'),
(15, 'Revision flotte avant evenement entreprise', '2026-05-14', '2026-05-18', 560.00, TRUE, 12.00, 4, 'Progression par lots'),
(16, 'Diagnostic panne eclairage et frein arriere', '2026-05-17', '2026-05-19', 48.00, TRUE, 1.00, 7, 'Client a annule apres diagnostic');

INSERT INTO invoice (client_id, order_id, repair_id, invoice_date, payment_method_id, payment_date, total_amount, is_paid) VALUES
(1, 1, NULL, '2026-01-12', 1, '2026-01-12', 652.48, TRUE),
(2, 2, NULL, '2026-01-21', 5, NULL, 594.98, FALSE),
(6, 3, NULL, '2026-02-06', 3, '2026-02-10', 1745.94, TRUE),
(5, 5, NULL, '2026-03-04', 3, NULL, 1509.96, FALSE),
(9, 9, NULL, '2026-04-18', 1, '2026-04-18', 675.64, TRUE),
(10, 10, NULL, '2026-04-22', 4, NULL, 805.98, FALSE),
(11, 11, NULL, '2026-04-26', 3, '2026-05-03', 376.90, TRUE),
(13, 13, NULL, '2026-05-05', 5, '2026-05-05', 781.19, TRUE),
(15, 15, NULL, '2026-05-14', 3, NULL, 1790.49, FALSE),
(1, NULL, 1, '2026-01-11', 2, '2026-01-11', 25.00, TRUE),
(3, NULL, 3, '2026-02-04', 4, '2026-02-04', 85.00, TRUE),
(5, NULL, 5, '2026-03-03', 1, NULL, 39.99, FALSE),
(8, NULL, 6, '2026-03-20', 4, '2026-03-20', 29.99, TRUE),
(9, NULL, 9, '2026-04-20', 2, '2026-04-20', 58.00, TRUE),
(11, NULL, 11, '2026-04-30', 3, NULL, 310.00, FALSE),
(14, NULL, 14, '2026-05-13', 1, '2026-05-13', 35.00, TRUE),
(16, NULL, 16, '2026-05-19', 4, NULL, 48.00, FALSE);
