# NyaaBot
A Discord bot providing general utilities such as calculation, and other fun
commands, including cat image generation!

Default prefix: ```!``` - run ```!help``` to see all user commands

Technologies used: JDA, Maven, Java 11, MySQL. 

To run, set up an environment variable DISCORD_TOKEN with the appropriate
bot token. 

For database access (including server-specific settings), 
set up DB_URL with a JDBC connection string, DB_USER and
DB_PASSWORD environment variables with the database credentials respectively. 

To enable developer (bot owner) commands, set environment variable BOT_OWNER
to your user ID. Then access the menu with ```!dev```.

Copyright (c) 2022, Ashlee Tan
