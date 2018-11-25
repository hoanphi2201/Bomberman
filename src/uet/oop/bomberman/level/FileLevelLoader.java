package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Doria;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.Ovape;
import uet.oop.bomberman.entities.character.enemy.Pass;
import uet.oop.bomberman.entities.character.enemy.Pontan;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;

import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

    // _map chứa thông tin được load từ file
    public static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelException {
        //TODO : lấy giá trị width height level từ file nạp vào
        try {
            InputStream inputStream = new FileInputStream("res/levels/level" + level + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String currentLine = bufferedReader.readLine();
            String[] firstLine = currentLine.split(" ");
            int Height = Integer.parseInt(firstLine[1]);
            int Width = Integer.parseInt(firstLine[2]);

            _map = new char[Height][Width];

            for (int i = 0; i < Height; i++) {
                String Line = bufferedReader.readLine();
                char[] charInLines = Line.toCharArray();
                for (int j = 0; j < Width; j++) {
                    _map[i][j] = charInLines[j];
                }
            }

            _width = Width;
            _height = Height;
            _level = level;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File level not found.");
        } catch (IOException e) {
            System.out.println("ERROR: Load file level.");
        }
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                addLevelEntity(_map[y][x], x, y);
            }
        }
    }

    public void addLevelEntity(char c, int x, int y) {
        int pos = x + y * getWidth();
        switch (c) {
            case '#':
                _board.addEntity(pos, new Wall(x, y, Sprite.wall));
                break;
            case 'b':
                LayeredEntity layer = new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));

                layer.addBeforeTop(new BombItem(x, y, _level, Sprite.powerup_bombs));

                _board.addEntity(pos, layer);
                break;
            case 's':
                layer = new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));

                layer.addBeforeTop(new SpeedItem(x, y, _level, Sprite.powerup_speed));

                _board.addEntity(pos, layer);
                break;
            case 'f':
                layer = new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick));

                layer.addBeforeTop(new FlameItem(x, y, _level, Sprite.powerup_flames));

                _board.addEntity(pos, layer);
                break;
            case '*':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Brick(x, y, Sprite.brick)));
                break;
            case 'x':
                _board.addEntity(pos, new LayeredEntity(x, y,
                        new Grass(x, y, Sprite.grass),
                        new Portal(x, y, _board, Sprite.portal),
                        new Brick(x, y, Sprite.brick)));
                break;
            case ' ':
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case 'p':
                _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                Screen.setOffset(0, 0);

                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            //Enemies có thể thêm các đối tượng địch khác 3,4,5 phiên bản sau
            case '1':
                _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '2':
                _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '3':
                _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '4':
                _board.addCharacter(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '5':
                _board.addCharacter(new Kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '6':
                _board.addCharacter(new Doria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '7':
                _board.addCharacter(new Ovape(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '8':
                _board.addCharacter(new Pontan(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            case '9':
                _board.addCharacter(new Pass(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
            default:
                _board.addEntity(pos, new Grass(x, y, Sprite.grass));
                break;
        }
    }
}
