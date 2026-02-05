-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 05 fév. 2026 à 14:23
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `campusmaster`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime(6) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `enseignant_id` bigint(20) DEFAULT NULL,
  `matiere_id` bigint(20) DEFAULT NULL,
  `semestre_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `date_creation`, `description`, `titre`, `enseignant_id`, `matiere_id`, `semestre_id`) VALUES
(1, '2025-12-25 16:39:23.000000', 'Initiation à la programmation', 'Programmation', 3, 1, 1),
(2, '2025-12-29 12:31:47.000000', 'Initiation à l\'algo', 'Algorithme', 3, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `depots_devoirs`
--

CREATE TABLE `depots_devoirs` (
  `id` bigint(20) NOT NULL,
  `date_depot` datetime(6) DEFAULT NULL,
  `url_fichier` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `devoir_id` bigint(20) DEFAULT NULL,
  `etudiant_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `depots_devoirs`
--

INSERT INTO `depots_devoirs` (`id`, `date_depot`, `url_fichier`, `version`, `devoir_id`, `etudiant_id`) VALUES
(1, '2025-12-25 16:49:12.000000', 'https://github.com/', 0, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `devoirs`
--

CREATE TABLE `devoirs` (
  `id` bigint(20) NOT NULL,
  `consigne` varchar(4000) DEFAULT NULL,
  `date_limite` datetime(6) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `cours_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `devoirs`
--

INSERT INTO `devoirs` (`id`, `consigne`, `date_limite`, `titre`, `cours_id`) VALUES
(1, 'réaliser le td 1 POO', '2025-12-25 16:45:00.000000', 'TD 1', 1),
(2, 'Deadline dans 2 jours', '2026-01-01 19:03:00.000000', 'TD 2', 2);

-- --------------------------------------------------------

--
-- Structure de la table `discussions`
--

CREATE TABLE `discussions` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  `cours_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `discussions`
--

INSERT INTO `discussions` (`id`, `date_creation`, `sujet`, `cours_id`) VALUES
(1, '2025-12-25 16:56:56.000000', 'Depôt devoir', 1);

-- --------------------------------------------------------

--
-- Structure de la table `matieres`
--

CREATE TABLE `matieres` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `matieres`
--

INSERT INTO `matieres` (`id`, `libelle`, `module_id`) VALUES
(1, 'poo', 1);

-- --------------------------------------------------------

--
-- Structure de la table `messages_discussion`
--

CREATE TABLE `messages_discussion` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(4000) DEFAULT NULL,
  `date_envoi` datetime(6) DEFAULT NULL,
  `auteur_id` bigint(20) DEFAULT NULL,
  `discussion_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `messages_discussion`
--

INSERT INTO `messages_discussion` (`id`, `contenu`, `date_envoi`, `auteur_id`, `discussion_id`) VALUES
(1, 'veuillez rendre le TD 1 avant jeudi', '2025-12-25 16:59:10.000000', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `messages_prives`
--

CREATE TABLE `messages_prives` (
  `id` bigint(20) NOT NULL,
  `contenu` varchar(4000) DEFAULT NULL,
  `date_envoi` datetime(6) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `destinataire_id` bigint(20) DEFAULT NULL,
  `expediteur_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `messages_prives`
--

INSERT INTO `messages_prives` (`id`, `contenu`, `date_envoi`, `tag`, `destinataire_id`, `expediteur_id`) VALUES
(1, 'jdjhggjkgjkgjkg', '2026-01-02 20:25:18.000000', 'PRIVE', 6, 2),
(2, 'bjr', '2026-01-02 20:40:22.000000', 'PRIVE', 1, 3),
(3, 'Bonsoir Mousieur, vous avez l\'autorisation de démarrer les cours', '2026-01-19 17:31:50.000000', 'PRIVE', 1, 2),
(4, 'Bonsoir, vous pouvez débuter les cours', '2026-02-02 21:04:35.000000', 'PRIVE', 3, 2),
(5, 'D\'accord', '2026-02-02 21:09:17.000000', 'PRIVE', 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `modules`
--

CREATE TABLE `modules` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `modules`
--

INSERT INTO `modules` (`id`, `code`, `libelle`) VALUES
(1, '1234', 'POO');

-- --------------------------------------------------------

--
-- Structure de la table `notes_devoirs`
--

CREATE TABLE `notes_devoirs` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(2000) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `depot_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `notes_devoirs`
--

INSERT INTO `notes_devoirs` (`id`, `commentaire`, `note`, `depot_id`) VALUES
(1, 'Trés bien', 17, 1);

-- --------------------------------------------------------

--
-- Structure de la table `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `lu` bit(1) NOT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `type_evenement` varchar(255) DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `notifications`
--

INSERT INTO `notifications` (`id`, `date_creation`, `lu`, `message`, `type_evenement`, `utilisateur_id`) VALUES
(1, '2025-12-25 17:02:22.000000', b'1', 'Les examens debuterons la semain prochaine', 'Examen semestre 1', 1),
(2, '2025-10-19 20:20:00.000000', b'1', 'Léa a déposé son devoir \"Calculateur IMC en JS\".', 'DEPOT_DEVOIR', 2),
(3, '2025-10-25 09:00:00.000000', b'0', 'Nouveau support de cours disponible : \"ML Fondamentaux\".', 'NOUVEAU_SUPPORT', 5),
(4, '2025-10-26 14:25:00.000000', b'0', 'Thomas vous a envoyé un message privé.', 'MESSAGE_PRIVE', 3);

-- --------------------------------------------------------

--
-- Structure de la table `semestres`
--

CREATE TABLE `semestres` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `semestres`
--

INSERT INTO `semestres` (`id`, `code`, `description`) VALUES
(1, 'S1', '2024 - 2025');

-- --------------------------------------------------------

--
-- Structure de la table `supports_cours`
--

CREATE TABLE `supports_cours` (
  `id` bigint(20) NOT NULL,
  `date_upload` datetime(6) NOT NULL,
  `nom_fichier` varchar(255) NOT NULL,
  `type` enum('AUTRE','PDF','PPT','VIDEO') NOT NULL,
  `url_fichier` varchar(255) NOT NULL,
  `version` int(11) NOT NULL,
  `cours_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `supports_cours`
--

INSERT INTO `supports_cours` (`id`, `date_upload`, `nom_fichier`, `type`, `url_fichier`, `version`, `cours_id`) VALUES
(1, '2025-12-25 16:43:03.000000', 'POO -S1', 'PDF', 'https://ton-site.com/devoirs/depot-10.zip', 1, 1),
(2, '2025-12-29 13:44:55.000000', 'Séance 1', 'AUTRE', 'https://github.com/', 1, 1),
(3, '2025-12-29 16:45:45.000000', 'Cours 1', 'AUTRE', 'https://github.com/', 1, 2),
(8, '2025-12-30 15:31:32.000000', 'Cours 2', 'PDF', 'https://openclassrooms.com/', 1, 2),
(14, '2025-12-30 21:07:21.000000', 'Cours 1', 'PDF', 'gitii.com', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` bigint(20) NOT NULL,
  `actif` bit(1) NOT NULL,
  `date_inscription` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom_complet` varchar(255) NOT NULL,
  `role` enum('ADMIN','ENSEIGNANT','ETUDIANT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `actif`, `date_inscription`, `email`, `mot_de_passe`, `nom_complet`, `role`) VALUES
(1, b'1', '2025-12-25 14:26:38.000000', 'etudiant1@campus.com', '$2a$10$2h.Xp6MjilGCWtx63bTdheWUpBsEJ8yEwYEYrzoCMMoordJpCUP0m', 'Etudiant Test', 'ETUDIANT'),
(2, b'1', '2025-12-25 15:46:24.000000', 'admin1@campus.com', '$2a$10$kvGWnlAuGwVyYqA9GCeYvuIoBTOgsOLT/yqu0DFO6jBZ1aiaETmFC', 'Admin Test', 'ADMIN'),
(3, b'1', '2025-12-25 15:49:03.000000', 'enseignant1@campus.com', '$2a$10$sA4vaP9BWNWANZ5r/3mvsuieWfR3wPfMHj3tXLY3XUHa07zTAQcIS', 'Enseignant Test', 'ENSEIGNANT'),
(4, b'1', '2025-12-27 13:41:18.000000', 'toure1@campus.com', '$2a$10$oHYnTZPo3bT.bxlGtpBk/Owi1kk5KcLYbLloubT.EgbFjSRLVnmqO', 'Macoumba Touré', 'ETUDIANT'),
(5, b'1', '2025-12-27 13:58:46.000000', 'khadim1@campus.com', '$2a$10$oWSm/DmZw5CJxkea2nPMw.5PyGO3P9No1CYJq4ZSyl1i2ZYIfvenC', 'Khadim Diop', 'ENSEIGNANT'),
(6, b'1', '2026-01-02 20:17:22.000000', 'talla@campus.com', '$2a$10$E.sAno032lNM51qUWRO3v.T7gE//Ti2DjfPgc8OMUiWeEd52ybDyy', 'talla diop', 'ETUDIANT');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkhc1cn8ttdxvmyjm1l9519mfa` (`enseignant_id`),
  ADD KEY `FKf5jb2m1n0rs7m7xp0tsxk1mwk` (`matiere_id`),
  ADD KEY `FKisv4e1kgiq3cv74ubyj6didm7` (`semestre_id`);

--
-- Index pour la table `depots_devoirs`
--
ALTER TABLE `depots_devoirs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg35b4nodcuy5e0tjnoi6492j5` (`devoir_id`),
  ADD KEY `FKl4bnimt3d73ngoyvdmjv994wt` (`etudiant_id`);

--
-- Index pour la table `devoirs`
--
ALTER TABLE `devoirs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKevlgidn81epksb04awi2cf51` (`cours_id`);

--
-- Index pour la table `discussions`
--
ALTER TABLE `discussions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5rvyiapg4sg16du4g873jypxv` (`cours_id`);

--
-- Index pour la table `matieres`
--
ALTER TABLE `matieres`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcej6evi28nmmv5clfv00sfirm` (`module_id`);

--
-- Index pour la table `messages_discussion`
--
ALTER TABLE `messages_discussion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsjsh7aauk44v42tb7bvlg9c26` (`auteur_id`),
  ADD KEY `FKs0tf2ptxkut7vwru6pdp8awcm` (`discussion_id`);

--
-- Index pour la table `messages_prives`
--
ALTER TABLE `messages_prives`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK19w0ew0e6ed6w6m5dfabq2wmt` (`destinataire_id`),
  ADD KEY `FKso2871bn8rvdt32stoqm2oqoo` (`expediteur_id`);

--
-- Index pour la table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `notes_devoirs`
--
ALTER TABLE `notes_devoirs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKei8ofym22rjipwqr7xlav3fbx` (`depot_id`);

--
-- Index pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsoq6jchv8p6wnydvsnf6ubw4y` (`utilisateur_id`);

--
-- Index pour la table `semestres`
--
ALTER TABLE `semestres`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `supports_cours`
--
ALTER TABLE `supports_cours`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcgx22t316q893q8swb24pnlqg` (`cours_id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6ldvumu3hqvnmmxy1b6lsxwqy` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `depots_devoirs`
--
ALTER TABLE `depots_devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `devoirs`
--
ALTER TABLE `devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `discussions`
--
ALTER TABLE `discussions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `matieres`
--
ALTER TABLE `matieres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `messages_discussion`
--
ALTER TABLE `messages_discussion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `messages_prives`
--
ALTER TABLE `messages_prives`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `modules`
--
ALTER TABLE `modules`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `notes_devoirs`
--
ALTER TABLE `notes_devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `semestres`
--
ALTER TABLE `semestres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `supports_cours`
--
ALTER TABLE `supports_cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `FKf5jb2m1n0rs7m7xp0tsxk1mwk` FOREIGN KEY (`matiere_id`) REFERENCES `matieres` (`id`),
  ADD CONSTRAINT `FKisv4e1kgiq3cv74ubyj6didm7` FOREIGN KEY (`semestre_id`) REFERENCES `semestres` (`id`),
  ADD CONSTRAINT `FKkhc1cn8ttdxvmyjm1l9519mfa` FOREIGN KEY (`enseignant_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `depots_devoirs`
--
ALTER TABLE `depots_devoirs`
  ADD CONSTRAINT `FKg35b4nodcuy5e0tjnoi6492j5` FOREIGN KEY (`devoir_id`) REFERENCES `devoirs` (`id`),
  ADD CONSTRAINT `FKl4bnimt3d73ngoyvdmjv994wt` FOREIGN KEY (`etudiant_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `devoirs`
--
ALTER TABLE `devoirs`
  ADD CONSTRAINT `FKevlgidn81epksb04awi2cf51` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);

--
-- Contraintes pour la table `discussions`
--
ALTER TABLE `discussions`
  ADD CONSTRAINT `FK5rvyiapg4sg16du4g873jypxv` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);

--
-- Contraintes pour la table `matieres`
--
ALTER TABLE `matieres`
  ADD CONSTRAINT `FKcej6evi28nmmv5clfv00sfirm` FOREIGN KEY (`module_id`) REFERENCES `modules` (`id`);

--
-- Contraintes pour la table `messages_discussion`
--
ALTER TABLE `messages_discussion`
  ADD CONSTRAINT `FKs0tf2ptxkut7vwru6pdp8awcm` FOREIGN KEY (`discussion_id`) REFERENCES `discussions` (`id`),
  ADD CONSTRAINT `FKsjsh7aauk44v42tb7bvlg9c26` FOREIGN KEY (`auteur_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `messages_prives`
--
ALTER TABLE `messages_prives`
  ADD CONSTRAINT `FK19w0ew0e6ed6w6m5dfabq2wmt` FOREIGN KEY (`destinataire_id`) REFERENCES `utilisateurs` (`id`),
  ADD CONSTRAINT `FKso2871bn8rvdt32stoqm2oqoo` FOREIGN KEY (`expediteur_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `notes_devoirs`
--
ALTER TABLE `notes_devoirs`
  ADD CONSTRAINT `FKa71jeedd6ogpsx8lhslq0k6gb` FOREIGN KEY (`depot_id`) REFERENCES `depots_devoirs` (`id`);

--
-- Contraintes pour la table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FKsoq6jchv8p6wnydvsnf6ubw4y` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `supports_cours`
--
ALTER TABLE `supports_cours`
  ADD CONSTRAINT `FKcgx22t316q893q8swb24pnlqg` FOREIGN KEY (`cours_id`) REFERENCES `cours` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
