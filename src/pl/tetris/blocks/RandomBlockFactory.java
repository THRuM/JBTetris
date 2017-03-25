package pl.tetris.blocks;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class RandomBlockFactory implements BlocksFactory {

    private Map<Class, Double> listOfBlockClasses;
    private List<Class> listOfBlocks;
    private Random random;

    public RandomBlockFactory(Map<Class, Double> listOfBlockClasses){
        this.listOfBlockClasses = listOfBlockClasses;
        listOfBlocks = new ArrayList<>();
        populateList();
    }

    public RandomBlockFactory() {
        random = new Random();
        listOfBlockClasses = new LinkedHashMap<>();
        listOfBlockClasses.put(LBlock.class, 0.25);
        listOfBlockClasses.put(SBlock.class, 0.25);
        listOfBlockClasses.put(SquareBlock.class, 0.25);
        listOfBlockClasses.put(TowerBlock.class, 0.25);

        listOfBlocks = new ArrayList<>();
        populateList();
    }

    private void populateList(){

        for(Class clas : listOfBlockClasses.keySet()){
            int number = (int) Math.round((listOfBlockClasses.size() * 2) * listOfBlockClasses.get(clas));

            for(int i=0; i < number; i++)
                listOfBlocks.add(clas);
        }
    }

    private Square randomColor(){
        Square[] colors = Square.values();
        return colors[random.nextInt(colors.length)];
    }

    @Override
    public Block getBlock() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class classOfBlock = listOfBlocks.get(random.nextInt(listOfBlocks.size()));
        Constructor constructor = classOfBlock.getConstructor(Square.class);

        return (Block) constructor.newInstance(randomColor());
    }

    @Override
    public List<Class> getListOfBlocks() {
        return listOfBlocks;
    }
}
