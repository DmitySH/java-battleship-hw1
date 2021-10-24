ENG:

Before launching the program, the user should decide how the parameters of the playing field will be set, the number of ships of each type, the number of torpedoes.
Two scenarios are possible:
1. start with command line parameters. In this case, every game the size of the field, the number of ships and torpedoes remain unchanged. In this case, you need to enter 8 parameters in this order and separated by a space: horizontal field size; vertical field size; quantity: carriers, battleships, cruisers, destroyers, submarines; number of torpedoes. At the same time, the number of torpedoes does not exceed the number of ships, and the number of cells that ships can occupy depends on the size of the field. In case of incorrect input, the user needs to restart the program with the correct parameters.
2. input from the console. This mode is active if the number of command line arguments is not equal to 8. In this case, the user enters the field sizes, the number of ships of each type, and the number of torpedoes each new game. A prompt is displayed for each input in the console. If the input is incorrect, a message is displayed that the input was incorrect. At the same time, if the user constantly enters incorrect data, the program ends (it is assumed that the user is tired and does not want to play anymore).

After launching the program, the user enters the game menu. It has 4 options to choose from:
play-play the sea battle
rules-read the short rules
score-see the best results of the games
exit-terminate the program.

A playing field is created on which the specified ships are randomly placed. If desired, the user can enable recovery mode, in which, in order to sink the ship, the user must hit it in a row until it sinks. In case of a miss, the ship is restored and you need to start firing at it as a whole.

During the game, the user enters coordinates in the format: letter (written on top of the field) digit (written to the left of the field).
Значение символов на поле:
o - по клетке не стреляли (или корабль на ней восстановлен)
- - промах (вскрытая клетка без корабля)
+ - попадание по кораблю
* - потопленный корабль
If the user intends to shoot a torpedo, then it is necessary to enter: T coordinates. The torpedo, when hit, instantly sinks the ship. All user actions are automatically displayed on the playing field. User can enter fleet to see how many ships of what type are left. The game continues until all the ships are sunk. Also, instead of coordinates, the user can enter finish, thereby instantly ending the game.
At the end of the game, the number of shots spent is shown. A shot is considered a hit on a cell that has not been fired before. The best results are recorded in the scoreboard.
After the end of the game, the user enters the menu again and can again select any of the options. The program terminates when exit is selected, incorrect data is entered from the command line, or an excessively large number of incorrect input attempts from the console.

RUS:

Перед запуском программы пользователю следует решить каким способом будут заданы параметры игрового поля, количество кораблей каждого типа, количество торпед.
Возможно два сценария:
1. запуск с параметрами командной строки. В данном случае каждую игру размер поля, количество кораблей и торпед остаются неизменными. В данном случае необходимо ввести 8 параметров в данном порядке и через пробел: горизонтальный размер поля; вертикальный размер поля; количество: carriers, battleships, cruisers, destroyers, submarines; количество торпед. При этом количество торпед не превышает число кораблей, а количество клеток, которые могут занять корабли, зависит от размера поля. В случае некорректного ввода пользователю необходимо перезапустить программу с корректными параметрами.
2. ввод с консоли. Этот режим активен, если число аргументов командной строки не равно 8. В данном случае пользователь каждую новую игру вводит размеры поля, количество кораблей каждого типа, количество торпед. Для каждого ввода в консоли выводится подсказка. При некорректном вводе выводится сообщение, что ввод был некорректен. При этом, если пользователь постоянно вводит некорректные данные, программа завершается(считается, что пользователь утомился и не хочет играть более).

После запуска программы пользователь попадает в игровое меню. В нем есть 4 опции на выбор:
play-играть в морской бой
rules-прочесть краткие правила
score-увидеть лучшие результаты игр
exit-завершить работу программы.

Создаётся игровое поле, на котором случайно расставляются заданные корабли. При желании пользователь может включить режим восстановления, при котором, чтобы потопить корабль, пользователю необходимо попадать в него подряд, пока он не утонет. При промахе корабль восстанавливается и по нему нужно начать огонь как по целому.

Во время игры пользователь вводит координаты в формате: буква(написаны сверху поля) цифра(написаны слева от поля).
Значение символов на поле:
o - по клетке не стреляли (или корабль на ней восстановлен)
- - промах (вскрытая клетка без корабля)
+ - попадание по кораблю
* - потопленный корабль

Если пользователь намерен выстрелить торпедой, то необходимо ввести: T координаты. Торпеда при попадании мгновенно топит корабль. Все действия пользователя автоматически отображаются на игровом поле. Также пользователь может написать fleet и увидеть, сколько кораблей какого типа осталось. Игра продолжается, пока все корабли не будут потоплены. Так же вместо координат пользователь может ввести finish, тем самым мгновенно окончив игру.
В конце игры показывается число затраченных выстрелов. За выстрел считается попадание по ещё не стреляной ранее клетке. Лучшие результаты заносятся в scoreboard.
После окончания игры пользователь опять попадает в меню и снова может выбрать любую из опций. Программа завершает при выборе exit, вводе некорректных данных из командной строки или при чрезмерно большом числе неправильных попыток ввода из консоли.
