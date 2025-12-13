-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 05 déc. 2025 à 18:41
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

-- --------------------------------------------------------

--
-- Structure de la table `matieres`
--

CREATE TABLE `matieres` (
  `id` bigint(20) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `module_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Structure de la table `modules`
--

CREATE TABLE `modules` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Structure de la table `semestres`
--

CREATE TABLE `semestres` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `depots_devoirs`
--
ALTER TABLE `depots_devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `devoirs`
--
ALTER TABLE `devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `discussions`
--
ALTER TABLE `discussions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `matieres`
--
ALTER TABLE `matieres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `messages_discussion`
--
ALTER TABLE `messages_discussion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `messages_prives`
--
ALTER TABLE `messages_prives`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `modules`
--
ALTER TABLE `modules`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notes_devoirs`
--
ALTER TABLE `notes_devoirs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `semestres`
--
ALTER TABLE `semestres`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `supports_cours`
--
ALTER TABLE `supports_cours`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

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
