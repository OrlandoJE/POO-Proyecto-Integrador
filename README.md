# Proyecto Integrador de Programación Orientada a Objetos

`MainClass.java` is the starting point. There, various settings can be adjusted at will.

This project has support for two kinds of games: platform with enemies and platform parkour.

## Buttons

All buttons (and graphical interfaces) are under the `ui` folder. Each button has its position and size.

For each type of button, there is a class which can be changed to add or modify the button's behavior.

## Custom assets

Directly in the res folder, you can add custom assets to the game. Be sure to follow the same naming conventions and the same file size in pixels.

## Custom levels

To add custom levels, you can create a new text file in the levels folder. The file must contain the following:

- Block index will be taken from the red value of the rgb color of the pixel. The index 0 will be the upper left corner of the asset, and then from left to right and top to bottom, the following index values will be assigned in increments of 1.
- Block index 11 (can be changed in `HelpMethods`) is transparent, therefore not stopping the player from moving through.
- The block will be placed in the position of the pixel.
- The player's initial position will be taken from the green value of the rgb color of the pixel, it must be 100.
- The enemy's initial position will be taken from the green value of the rgb color of the pixel, it must be 0.
- For parkour mode, winning position will be taken from the blue value of the rgb color of the pixel, it must be 200.

## Recomendations

- Check the `res` folder to see the assets and levels that are already in the game. If wished (and recommended) they can be modify and it is advised to follow the same pattern as the assets in the `res` folder.
- If not followed, modify the `GetLevelData` in the `HelpMethods` class to adjust to the new assets.
- For the levels, it is recommended to use the same height as the ones in the `res` folder, width can vary.

> This is a modified version of the _Java Game Development Tutorial Platformer_ by **Kaarin Gaming**. This is for educational porpuses and non-commercial.
