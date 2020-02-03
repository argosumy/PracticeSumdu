package ua.edu.sumdu.j2se.valeriy.tasks.view;

public class ShowMenu implements View {
    public static String [] hadMenu = new String[] {"1. Make new TASK","2. Worke with Task list","3. Show TASK list ","0. EXIT"};
    public static String [] workeMenu = new String[] {"1. Make new TASK","2. Delete TASK","3. Edit TASK ", "0. Had menu" };
    public static String [] editMenu = new String [] {"1.Title","2.Start/End/Replay", "3.Task active / inactive","0.Work  menu" };

    public ShowMenu() {
    }


    @Override
    public Boolean show(Object men) {
        String[] menuShow = (String[]) men;
        int len;
        int maxLen = 0;
        for (String menu : menuShow){
            System.out.println(menu);
            len = menu.length();
            if(len > maxLen){
                maxLen = len;
            }
        }
        for(int i = 0; i <= maxLen; i++){
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Select an action  ");
        System.out.print("From 0 To " + (menuShow.length - 1) + "  -  ");
        return true;
    }
}
