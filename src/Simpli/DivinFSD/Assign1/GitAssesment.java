package Simpli.DivinFSD.Assign1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GitAssesment {
  static int mainmenu_flag = 1, opn_tem_sig = 1;;
  static String dir_path, search_key;
  static ArrayList < String > filesnames = new ArrayList<String>();
  static Scanner global_sc = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    introScreen();
    main_menu_screen();
    try {
      do {
        mainmenu_flag = global_sc.nextInt();
        switch (mainmenu_flag) {
        case 1:
          String termsig;
          do {
            listing_sort();
            Screen_divider();
            System.out.print("Do you want to contine with the selected path press Y to confirm  \n press N if you want to change ");
            termsig = global_sc.next().toUpperCase();
            if (termsig.equals("Y")) {
              Screen_divider();
              System.out.println("Current Operating Directory : " + dir_path);
              break;
            } else {
              filesnames.clear();
            }
          } while (true);
          main_menu_screen();
          break;
        case 2:
          if (dir_path == null) {
            dir_path = System.getProperty("user.dir");
            Screen_divider();
            System.out.println("Current Operating Directory : " + dir_path);

            basic_operation();

          } else {
            basic_operation();
          }
          main_menu_screen();
          break;
        case 3:
          exit_scren();
          break;
        default:
          error_scren();
          main_menu_screen();
          break;
        }
        if (mainmenu_flag == 3) {
          break;
        }
      } while (mainmenu_flag != 3);
    } catch (Exception e) {
      System.out.println("Invalid inputs recived exiting application");
      
      exit_scren();
    }

  }

  static void basic_operation() throws IOException {
    operation_menu_screen();
    int operation_flag = global_sc.nextInt();

    switch (operation_flag) {
    case 1:
      System.out.print("Enter name of the file you want to add :");
      String new_filename = global_sc.next();

      if (filesnames.contains(new_filename)) {
        System.out.println("We regret to inform you filename is already taken \n Please try again");
      } else {
        File filecreateholder = new File(dir_path+"/"+new_filename);
        if (filecreateholder.createNewFile()) {
          System.out.println("File created successfully");
          refreshlist();
        } else {
          System.out.println("File creation failed please connect with administrator");
        }
      }
      basic_operation();
      refreshlist();
      break;
    case 2:
      System.out.print("Enter name of the file you want to delete :");
      String delete_filename = global_sc.next();
      refreshlist();
      if (filesnames.contains(delete_filename)) {
        File filedeleteholder = new File(dir_path+"/"+delete_filename);
        if (filedeleteholder.delete()) {
          System.out.println("File delete successfully");
          refreshlist();
        }

      } else {
        System.out.println("File not found");
      }
      basic_operation();
      break;
    case 3:
      System.out.print("Enter the file name to search");
      search_key = global_sc.next();
      refreshlist();
      int res = binarySh(search_key);
      if (res == -1)
        System.out.println("File not found please try again");
      else
        System.out.println("File found at " + (res + 1) + " Location");
      basic_operation();
      break;
    case 4:
      refreshlist();
      opn_tem_sig = 0;
      break;
    default:
    	error_scren();
    	operation_menu_screen();
    }

  }

  static void refreshlist() {
    filesnames.clear();
    File folder = new File(dir_path);
    File[] listOfFiles = folder.listFiles();
    for (File file: listOfFiles) {
      if (file.isFile()) {
        filesnames.add(file.getName().toString());
      }
    }
  }
  static void listing_sort() {
    System.out.print("Note : Executing in default directory Press Y if you want to change directory else press N to continue ");
    String directory_stat = global_sc.next().toUpperCase();
    if (directory_stat.equals("Y")) {
      System.out.print("Please enter the directory path :");
      dir_path = global_sc.next();
      refreshlist();
      if(filesnames.isEmpty()) {
    	  System.out.println("No files found to list in this directory");
      }else {
      System.out.println("Files available in " + dir_path + " are \n");
      for (String datas: filesnames) {
        System.out.println(datas);
      }
      }
    }
    if (directory_stat.equals("N")) {
      dir_path = System.getProperty("user.dir");
      refreshlist();
      System.out.println("Files available in " + dir_path + " are \n");
      for (String datas: filesnames) {
        System.out.println(datas);
      }
    }
  }
  static void introScreen() {
    System.out.println(" _______________________________________________");
    System.out.println("|                                               |");
    System.out.println("|              * Git Assesment *                |");
    System.out.println("|_______________________________________________|");
    System.out.println("|                              Developer Details|");
    System.out.println("|                          Name : Divin Kumar SE|");
    System.out.println("|                   Email:divinkumarse@gmail.com|");
    System.out.println("|_______________________________________________|");
  }
  static void main_menu_screen() {
    System.out.println(" ________________________________________________");
    System.out.println("|                 * Main Menu *                  |");
    System.out.println("|           Thank you for choosing us            |");
    System.out.println("|________________________________________________|");
    System.out.println("| 1. List the files                              |");
    System.out.println("| 2. Open operations window                      |");
    System.out.println("| 3. Exit Application                            |");
    System.out.println("| Please select an appropriate option to proceed |");
    System.out.println("|________________________________________________|");
  }
  static void operation_menu_screen() {
    System.out.println(" ________________________________________________");
    System.out.println("|              * Operations Menu *               |");
    System.out.println("|________________________________________________|");
    System.out.println("| 1. Add file                                    |");
    System.out.println("| 2. Remove file                                 |");
    System.out.println("| 3. Search file                                 |");
    System.out.println("| 4. Exit to main menu                           |");
    System.out.println("| Please select an appropriate option to proceed |");
    System.out.println("|________________________________________________|");
  }
  static void error_scren() {
    System.out.println(" _________________________________________________________________");
    System.out.println("|* Invalid Selection please select any one option from below menu*|");
    System.out.println("|_________________________________________________________________|");
  }
  static void exit_scren() {
    System.out.println(" ________________________________________________________________");
    System.out.println("|             * Thank you for using our services *               |");
    System.out.println("|________________________________________________________________|");
  }
  static void Screen_divider() {
    System.out.println("____________________________________________________________________________________________________");
  }
  static int binarySh(String key) {
    int l = 0, r = filesnames.size() - 1;
    while (l <= r) {
      int m = l + (r - l) / 2;

      int res = key.compareTo(filesnames.get(m));
      if (res == 0)
        return m;
      if (res > 0)
        l = m + 1;
      else
        r = m - 1;
    }
    return -1;
  }

}