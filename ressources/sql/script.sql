CREATE DATABASE mdd;

USE mdd;

CREATE TABLE `themes`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT,
    `title`       VARCHAR(255),
    `description` TEXT(65535)
);

CREATE TABLE `users`
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `username`   VARCHAR(255),
    `email`      VARCHAR(255),
    `password`   VARCHAR(255),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `subscriptions`
(
    `user_id`  INT,
    `theme_id` INT,
    PRIMARY KEY (`user_id`, `theme_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`)
);

CREATE TABLE `articles`
(
    `id`       INT PRIMARY KEY AUTO_INCREMENT,
    `title`    VARCHAR(500),
    `content`  TEXT(65535),
    `author`   VARCHAR(500),
    `date`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `theme_id` INT,
    FOREIGN KEY (`theme_id`) REFERENCES `themes` (`id`)
);

CREATE TABLE `comments`
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `content`    TEXT(65535),
    `author`     VARCHAR(500),
    `date`       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `article_id` INT,
    FOREIGN KEY (`article_id`) REFERENCES `articles` (`id`)
);

INSERT INTO users (username, email, password, created_at, updated_at)
    # password: test!1234
VALUES ('Admin', 'admin@mdd.com', '$2a$10$OrLIi.J1M2Y19h.QfIp3.e3BW7LxRuunsNHFQm.gnp60hwo.5fCci', null, null);


INSERT INTO themes (title, description)
VALUES ('Java',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.'),
       ('Angular',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.'),
       ('Javascript',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.'),
       ('C#',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.'),
       ('Python',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.');

INSERT INTO subscriptions (user_id, theme_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);

INSERT INTO `articles` (`title`, `content`, `author`, `date`, `theme_id`)
VALUES ('Python : Un langage de programmation puissant et polyvalent',
        'Python est un langage de programmation populaire et polyvalent qui a gagné en popularité ces dernières années. Avec sa syntaxe claire et concise, il est considéré comme l''un des langages les plus faciles à apprendre pour les débutants. Cependant, ne vous laissez pas tromper par sa simplicité, car Python est également un langage extrêmement puissant utilisé dans de nombreux domaines, tels que le développement web, l''intelligence artificielle, l''analyse de données et bien d''autres. Sa vaste bibliothèque standard et sa communauté active font de Python un choix idéal pour les développeurs de tous niveaux.',
        'John Doe', '2022-01-15 10:30:00', 5),
       ('Python : Un outil essentiel pour l\'analyse de données',
        'Dans le domaine de l\'analyse de données, Python est devenu un outil incontournable. Avec des bibliothèques telles que NumPy, Pandas et Matplotlib, Python offre un large éventail de fonctionnalités pour manipuler, analyser et visualiser les données. Que vous ayez besoin d''explorer un ensemble de données volumineux, de créer des graphiques interactifs ou d''effectuer des calculs statistiques avancés, Python dispose des outils nécessaires pour faciliter ces tâches. Sa syntaxe claire et expressive en fait un choix populaire parmi les scientifiques des données et les analystes.',
        'Jane Smith', '2022-03-02 14:45:00', 5),
       ('Python dans le monde du développement web',
        'Python est également un choix populaire pour le développement web. Avec des frameworks tels que Django et Flask, Python offre une approche simple et efficace pour la création d''applications web robustes. Les développeurs apprécient la lisibilité du code Python, qui favorise la collaboration et la maintenabilité à long terme des projets. De plus, Python bénéficie d''une grande variété de bibliothèques pour gérer les tâches courantes du développement web, telles que le traitement des requêtes HTTP, l''interfaçage avec les bases de données et la gestion des sessions utilisateur.',
        'Robert Johnson', '2022-05-18 09:15:00', 5),
       ('Python et l\'intelligence artificielle', 'Python est devenu le langage de prédilection pour de nombreux projets d''intelligence artificielle et d''apprentissage automatique. Des bibliothèques telles que TensorFlow,
        Keras et PyTorch fournissent des outils puissants pour créer,
        former et déployer des modèles d''IA. Python offre également une grande flexibilité en matière de prétraitement des données,
        de visualisation des résultats et d''intégration avec d''autres outils et langages. Avec Python,
        les chercheurs et les développeurs peuvent mettre en œuvre des solutions d''IA sophistiquées et transformer des idées novatrices en réalité.',
        'Emily Wilson', '2022-07-07 16:20:00', 5),
       ('La communauté Python : Collaboration et partage', 'L''une des forces de Python réside dans sa communauté active et engagée. Des milliers de développeurs à travers le monde contribuent à l''amélioration du langage,
        créent des bibliothèques open source et partagent leurs connaissances et leurs expériences. Les forums de discussion,
        les listes de diffusion et les plateformes de partage de code sont abondants, ce qui facil', 'Michael Brown',
        '2022-09-22 11:10:00', 5),
       ('C# : Un langage polyvalent pour le développement logiciel', 'C# est un langage de programmation polyvalent largement utilisé pour le développement logiciel. Il est prisé pour sa syntaxe claire et expressive,
        qui facilite la création d''applications robustes et évolutives. Que vous souhaitiez créer des applications de bureau,
        des applications web ou des applications mobiles,
        C# offre les outils et les fonctionnalités nécessaires pour relever ces défis. De plus,
        C# est intégré à l''écosystème .NET,
        ce qui lui permet de bénéficier d''une vaste bibliothèque de classes et de frameworks prêts à l''emploi.',
        'Alex Turner', '2022-02-10 11:30:00', 4),
       ('C# : Le langage de référence pour le développement d\'applications Windows',
        'Si vous êtes intéressé par le développement d''applications Windows, C# est le langage de référence. Grâce à sa forte intégration avec la plateforme Windows et à son support complet de l''API Windows, C# permet de créer des applications natives performantes et riches en fonctionnalités. Avec les outils de développement tels que Visual Studio, les développeurs C# peuvent concevoir des interfaces utilisateur attrayantes, accéder aux fonctionnalités système et créer des applications qui tirent pleinement parti des capacités de Windows.',
        'Sophia Johnson', '2022-04-05 15:45:00', 4),
       ('C# et l\'internet des objets : Une combinaison puissante', 'L''internet des objets (IoT) est en pleine expansion,
        et C# se positionne comme l''un des langages les plus adaptés pour développer des applications IoT. Grâce à sa compatibilité avec le framework .NET Core,
        C# peut être utilisé pour créer des applications qui interagissent avec des dispositifs connectés,
        collectent et traitent des données en temps réel,
        et contrôlent des objets à distance. L''écosystème .NET offre également des bibliothèques et des outils spécifiques à l''IoT,
        ce qui facilite le développement d''applications intelligentes et sécurisées.', 'David Roberts',
        '2022-06-20 10:15:00', 4),
       ('C# et le développement de jeux vidéo', 'Le développement de jeux vidéo est un domaine exigeant qui requiert des performances élevées et une gestion complexe des ressources. C# s''est établi comme l''un des langages privilégiés pour le développement de jeux grâce à des moteurs de jeu populaires tels qu''Unity. En utilisant C# avec Unity,
        les développeurs peuvent créer des jeux multiplateformes captivants et immersifs. C# offre une syntaxe facile à lire,
        un garbage collector efficace et une intégration transparente avec les outils de conception et d''animation,
        ce qui en fait un choix populaire pour les développeurs de jeux vidéo.', 'Emma Thompson', '2022-08-11 17:20:00',
        4),
       ('C# et le développement web avec ASP.NET', 'ASP.NET est un framework de développement web puissant basé sur C#. En utilisant C# avec ASP.NET,
        les développeurs peuvent créer des sites web dynamiques,
        des API web et des applications web à grande échelle. C# offre une combinaison de facilité d''utilisation et de performances élevées,
        ce qui permet de développer des applications web réactives et fiables. De plus,
        ASP.NET bénéficie d''une intégration étroite avec Visual Studio,
        offrant des outils de développement avancés tels que le débogage,
        la gestion des dépendances et le déploiement simplifié.', 'Michael Davis', '2022-10-28 12:10:00', 4),
       ('JavaScript : Le langage de programmation incontournable du web', 'JavaScript est le langage de programmation incontournable pour le développement web. Il est principalement utilisé pour rendre les sites web interactifs et dynamiques. Grâce à son intégration étroite avec HTML et CSS,
        JavaScript permet de manipuler le contenu de la page,
        de répondre aux interactions de l''utilisateur et de communiquer avec les serveurs. De plus,
        JavaScript est pris en charge par tous les principaux navigateurs web,
        ce qui en fait un choix universel pour le développement front-end.', 'Sarah Johnson', '2022-01-05 09:30:00', 3),
       ('JavaScript côté serveur avec Node.js : Une nouvelle ère pour le développement web', 'Avec l''avènement de Node.js,
        JavaScript peut également être utilisé côté serveur. Node.js est une plateforme qui permet d''exécuter du code JavaScript en dehors du navigateur,
        ce qui ouvre de nouvelles possibilités pour le développement web. Grâce à Node.js,
        les développeurs peuvent créer des serveurs web rapides et évolutifs, des API RESTful,
        des applications en temps réel et bien plus encore. JavaScript côté serveur offre une approche unifiée du développement web,
        permettant aux développeurs de travailler avec le même langage des deux côtés de l''application.',
        'Daniel Thompson', '2022-03-20 13:45:00', 3),
       ('Les frameworks JavaScript les plus populaires pour le développement web', 'Le développement web moderne repose souvent sur l''utilisation de frameworks JavaScript. Ces frameworks fournissent des fonctionnalités et des outils avancés qui facilitent la création d''applications web complexes. Parmi les frameworks les plus populaires, on
        trouve Angular, React et Vue.js. Angular est un framework complet développé par Google,
        React est une bibliothèque JavaScript utilisée pour construire des interfaces utilisateur interactives,
        et Vue.js est un framework progressif et intuitif. Chacun de ces frameworks a ses propres avantages et est largement utilisé par les développeurs web.',
        'Emma Roberts', '2022-05-12 10:15:00', 3),
       ('JavaScript et les applications mobiles hybrides', 'JavaScript est également utilisé pour le développement d''applications mobiles hybrides. Les applications hybrides combinent des technologies web,
        telles que HTML, CSS et JavaScript,
        avec des conteneurs natifs pour permettre une expérience utilisateur similaire à celle des applications natives. Des frameworks tels que Ionic et React Native permettent de créer des applications mobiles hybrides performantes et multiplateformes en utilisant JavaScript. Cela offre aux développeurs la possibilité de réutiliser le code et d''accéder à des fonctionnalités natives des appareils mobiles.',
        'Michael Davis', '2022-07-28 15:20:00', 3),
       ('JavaScript et l\'avenir du développement logiciel',
        'JavaScript a connu une croissance exponentielle ces dernières années et son avenir est prometteur. De plus en plus de développeurs adoptent JavaScript pour une variété de cas d''utilisation, allant du développement web au développement d''applications mobiles et même au développement de l''internet des objets (IoT). Avec l''émergence de nouvelles spécifications, telles que ECMAScript 6 (ES6) et les avancées dans les frameworks et les outils, JavaScript continue d''évoluer pour répondre aux besoins changeants du développement logiciel. Son polyvalence, sa large adoption et sa communauté dynamique en font un langage clé pour l''avenir du développement logiciel.',
        'Emily Wilson', '2022-09-15 11:10:00', 3),
       ('Angular : Le framework JavaScript pour le développement web moderne',
        'Angular est un framework JavaScript puissant et populaire utilisé pour le développement web moderne. Il offre une structure solide pour la création d''applications web évolutives et performantes. Grâce à sa facilité d''utilisation et à sa riche bibliothèque de composants, Angular permet aux développeurs de construire des interfaces utilisateur réactives et dynamiques. Avec son architecture basée sur les composants, Angular favorise la modularité et la réutilisabilité du code, ce qui facilite la maintenance et l''évolution des applications web.',
        'David Johnson', '2022-01-12 14:30:00', 2),
       ('Les fondamentaux d\'Angular : Composants, directives et modules', 'Pour maîtriser Angular,
        il est essentiel de comprendre les concepts de base tels que les composants,
        les directives et les modules. Les composants sont les blocs de construction fondamentaux d''une application Angular,
        tandis que les directives permettent de modifier le comportement du DOM. Les modules regroupent les fonctionnalités connexes de l''application. En comprenant ces concepts clés,
        les développeurs peuvent exploiter pleinement le potentiel d''Angular et créer des applications web robustes et évolutives.',
        'Sophia Roberts', '2022-03-05 09:45:00', 2),
       ('Gestion de l\'état avec Angular : Introduction à NgRx',
        'La gestion de l''état est un aspect critique du développement d''applications web complexes. Angular offre une solution élégante à ce problème avec NgRx, une bibliothèque de gestion de l''état inspirée de Redux. NgRx permet de centraliser et de gérer l''état de l''application de manière prévisible et maintenable. En utilisant des concepts tels que les actions, les réducteurs et les sélecteurs, les développeurs peuvent créer des applications Angular qui sont faciles à tester, à déboguer et à maintenir.',
        'Alex Turner', '2022-05-18 16:15:00', 2),
       ('Angular et la création d\'applications mobiles hybrides avec Ionic', 'Ionic est un framework open source basé sur Angular qui permet de créer des applications mobiles hybrides. En combinant la puissance d''Angular avec les capacités multiplateformes de Ionic,
        les développeurs peuvent créer des applications mobiles performantes et attrayantes. Ionic offre une gamme de composants prêts à l''emploi,
        une interface utilisateur réactive et une intégration transparente avec les fonctionnalités natives des appareils. Avec Angular et Ionic,
        il est possible de développer une application une fois et de la déployer sur plusieurs plateformes,
        comme iOS et Android.', 'Emma Davis', '2022-07-07 10:20:00', 2),
       ('Déploiement d\'une application Angular sur le cloud avec Firebase',
        'Firebase est une plateforme de développement d''applications en cloud offrant divers services utiles aux développeurs. L''intégration d''Angular avec Firebase facilite le déploiement d''applications Angular sur le cloud. Firebase propose des fonctionnalités telles que l''hébergement web, l''authentification des utilisateurs, la base de données en temps réel et bien d''autres. En combinant Angular et Firebase, les développeurs peuvent déployer rapidement et facilement leurs applications web Angular, tout en bénéficiant de fonctionnalités backend prêtes à l''emploi et d''une mise à l''échelle automatique.',
        'Michael Wilson', '2022-09-30 13:10:00', 2),
       ('Java : Le langage de programmation polyvalent et robuste',
        'Java est un langage de programmation polyvalent et robuste largement utilisé dans le développement logiciel. Il offre une plateforme indépendante et est adapté à une variété de domaines, tels que le développement d''applications de bureau, le développement web, les applications mobiles et même l''Internet des objets. Grâce à sa syntaxe claire, sa gestion automatique de la mémoire et sa vaste bibliothèque standard, Java permet aux développeurs de créer des applications fiables et évolutives.',
        'Olivia Smith', '2022-01-18 11:30:00', 1),
       ('Les principes de base de la programmation orientée objet en Java',
        'Java est un langage de programmation orienté objet, ce qui signifie qu''il se base sur les principes de l''orienté objet tels que l''encapsulation, l''héritage et le polymorphisme. Comprendre ces principes est essentiel pour exploiter pleinement le potentiel de Java. L''encapsulation permet d''encapsuler les données et le comportement d''un objet, l''héritage permet de créer des relations entre les classes, et le polymorphisme permet de traiter des objets de différentes classes de manière uniforme. La maîtrise de la programmation orientée objet en Java ouvre de nombreuses possibilités de conception et de développement d''applications.',
        'William Johnson', '2022-03-10 14:45:00', 1),
       ('Les collections en Java : Manipulation et gestion des données',
        'Les collections en Java sont des structures de données prédéfinies qui permettent de manipuler et de gérer des ensembles d''objets. Elles offrent une variété de types de collections tels que les listes, les ensembles et les cartes, ainsi que des opérations pour ajouter, supprimer, rechercher et parcourir les éléments. Les collections en Java simplifient le travail avec des ensembles de données et offrent des performances optimisées. Comprendre comment utiliser efficacement les collections est essentiel pour le développement d''applications Java.',
        'Sophie Roberts', '2022-05-22 09:15:00', 1),
       ('Java et le développement d\'applications Android', 'Java est le langage de programmation principal utilisé pour le développement d''applications Android. Grâce à la plateforme Android,
        les développeurs peuvent créer des applications mobiles puissantes et innovantes pour les smartphones et les tablettes. Java fournit les outils et les bibliothèques nécessaires pour développer des fonctionnalités avancées,
        interagir avec les composants du système Android et créer des expériences utilisateur engageantes. Le développement d''applications Android en Java offre une grande flexibilité et ouvre la voie à une vaste communauté de développeurs.',
        'Matthew Davis', '2022-07-13 14:20:00', 1),
       ('Java et l\'Internet des objets (IoT)',
        'L''Internet des objets (IoT) est un domaine en pleine expansion qui consiste à connecter des objets physiques à Internet. Java est un choix populaire pour le développement d''applications IoT en raison de sa portabilité, de sa sécurité et de sa grande communauté de développeurs. Java offre des bibliothèques et des frameworks adaptés à l''IoT, ainsi que des outils pour la gestion des données, la communication entre appareils et la création d''applications IoT évolutives. Avec Java, les développeurs peuvent créer des solutions innovantes pour l''Internet des objets.',
        'Emily Wilson', '2022-09-28 10:10:00', 1)
;

INSERT INTO `comments` (content, author, article_id)
VALUES ('Très bon article !', 'John Smith', 1),
       ('Merci pour cet article !', 'Jane Doe', 1),
       ('J''ai appris beaucoup de choses !', 'John Smith', 2),
       ('Merci pour cet article !', 'Jane Doe', 2),
       ('Très bon article !', 'John Smith', 3),
       ('Merci pour cet article !', 'Jane Doe', 3),
       ('J''ai appris beaucoup de choses !', 'John Smith', 4),
       ('Merci pour cet article !', 'Jane Doe', 4),
       ('Très bon article !', 'John Smith', 5),
       ('Merci pour cet article !', 'Jane Doe', 5),
       ('J''ai appris beaucoup de choses !', 'John Smith', 6),
       ('Merci pour cet article !', 'Jane Doe', 6),
       ('Très bon article !', 'John Smith', 7),
       ('Merci pour cet article !', 'Jane Doe', 7),
       ('J''ai appris beaucoup de choses !', 'John Smith', 8),
       ('Merci pour cet article !', 'Jane Doe', 8),
       ('Très bon article !', 'John Smith', 9),
       ('Merci pour cet article !', 'Jane Doe', 9),
       ('J''ai appris beaucoup de choses !', 'John Smith', 10),
       ('Merci pour cet article !', 'Jane Doe', 10),
       ('Très bon article !', 'John Smith', 11),
       ('Merci pour cet article !', 'Jane Doe', 11),
       ('J''ai appris beaucoup de choses !', 'John Smith', 12),
       ('Merci pour cet article !', 'Jane Doe', 12),
       ('Très bon article !', 'John Smith', 13),
       ('Merci pour cet article !', 'Jane Doe', 13),
       ('J''ai appris beaucoup de choses !', 'John Smith', 14),
       ('Merci pour cet article !', 'Jane Doe', 14),
       ('Très bon article !', 'John Smith', 15),
       ('Merci pour cet article !', 'Jane Doe', 15);
