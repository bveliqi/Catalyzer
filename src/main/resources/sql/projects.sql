INSERT INTO `USERS` (`name`, `avatar`, `motivation`, `subtitle`) VALUES
('Stephanie Stroka', '1', "I started Catalyzer with Alejo and Behar.  I'm very happy that we've managed to turn this site into such a positive force for good!", 'I hack for fun and profit!'),
('Alejandro Forero Cuervo', '2', 'Lets...', 'Lets see how far we can go!'),
('Blue Dragon', '3', "I'm a very secretive person, but I want to help make the world better!", "Growl!"),
('Silvio Valenti', '4',
  "I signed up on Catalyzer because my friends Alejo and Steffi kept bugging me.  Since I gave it a try, I've participated in a few nice projects.\n\nNow I'm planning a few projects of my own, we'll see how it goes.",
  "Oh sole mio!");

INSERT INTO `PROJECTS` (`authorId`, `name`, `motivation`, `photoUrl`, `category`, `pointsThreshold`, `status`, `startDate`, `endDate`, `longitude`, `latitude`, `points`) VALUES
(1, 'Clean up the Sihl',
 "Some parts of the Sihl river -close to Tuefi- have become relatively polluted.  We will gather there on a Saturday and spend a few hours just collecting garbage.  Since the Tuefi closes at 12, we'll start early.\n\nThen, depending on the weather, we will drink some beers and cook some sausages.\n\nJoin us and help us keep our river beautiful.",
 'img/garbage_picking.jpg', 'Environment', 100, 'OK', '2014-10-18 12:25:19', NULL, NULL, NULL, 200),
(2, 'Clothes for Colombia',
 "I'll be travelling to Colombia soon and I figured I would bring some clothes.  Want to reclaim some space in your wardrobe and help someone in need?\n\nI'll take donation until I fill two bags.",
 '.jpg', 'Welfare', 30, 'OK', '2014-11-20 12:32:36', NULL, NULL, NULL, 0),
(3, 'Lets swap books!',
 "Hello, fellow readers!\n\nWe'll meet in Big Ben Pub soon to exchange books.  Bring up to two books that you've read recently and would like to give away.",
 'img/books.jpg', 'Education', 30, 'OK', '2014-10-17 20:00:00', NULL, NULL, NULL, 0),
(4, 'Help us Reforest the Shasta Trinity National Forest',
 "The wildfire in Boles recently torched nearly 500 acres in California, near the Oregon border.  :-(\n\nWe will spend the whole weekend planting as many trees as we can.  We need volunteers to:\n\n1. Transport (by car) plants from the greenhouse in the nearby city of Redding (which are donated by the Californian government).\n2. Plant the trees in the Shasta Trinity National Forest, under the direction of the Forest rangers.\n\nSleeping arrangements will be made in cabbins in the forest, for those who want to spend Saturday night in the park (bring a sleeping bag!).  More instructions will follow, but space is limited to 20 people.\n\nHelp us make our best to recover our Forest!",
 'img/planting_trees.jpg', 'Environment', 30, 'OK', '2014-10-11 13:13:06', NULL, 0, 0, 0)
;
