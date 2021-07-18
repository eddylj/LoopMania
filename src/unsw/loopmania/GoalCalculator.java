package unsw.loopmania;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalCalculator {
    //private JSONObject json;
    private Character character;
    private Composite checker;

    public GoalCalculator(JSONObject json, Character character) {
        //this.json = json;
        this.character = character;
        this.checker = winCondition(json);
    }

    /**
     * Checks whether win condition has been met
     * @return boolean
     */
    public boolean checkWinCondition() {
        return checker.getValue();
    }

    /**
     * Parses json and discerns winning conditions
     * @param json
     * @return Composite 
     */
    public Composite winCondition(JSONObject json) {
        if (json.has("quantity")) {
            Composite leaf = new CompositeLeaf((int)json.get("quantity"), character, (String)json.get("goal"));
            return leaf;
        }
        else {
            JSONArray subgoals = json.getJSONArray("subgoals");
            JSONObject left = (JSONObject)subgoals.get(0);
            JSONObject right = (JSONObject)subgoals.get(1);
            if (((String)json.get("goal")).equals("AND")) {
                Composite and = new CompositeAnd(winCondition(left), winCondition(right));
                return and;
            }
            else {
                Composite or = new CompositeOr(winCondition(left), winCondition(right));
                return or;
            }

        }
    }

    public Composite getChecker() {
        return checker;
    }

    public int getMax(String type) {
        return checker.getMax(type);
    }
}
