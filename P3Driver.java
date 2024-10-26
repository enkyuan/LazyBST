/*
 * Name:    Enkang Yuan
 * Sect.:   CS 3345.503
 * Desc.:   This is the driver class for this project. It reads the input file and performs the operations on the lazy binary search tree.   
 */

// import statements
import java.io.*;
import java.util.Scanner;

public class P3Driver {
    public static void main(String[] args) {
        // check if the correct number of arguments are provided
        if (args.length != 2) {
            System.err.println("Usage: java Main <input_file> <output_file>");
            System.exit(1);
        }

        // retrieve the input and output file names from arguments
        String inputFileName = args[0];
        String outputFileName = args[1];

        // create an instance of the LazyBST
        LazyBST tree = new LazyBST();
        // tnitialize a stringbuilder to accumulate the output
        StringBuilder output = new StringBuilder();

        try (
            // parse input file for reading
            Scanner scanner = new Scanner(new File(inputFileName));
            // parse output file for writing 
            PrintWriter writer = new PrintWriter(new File(outputFileName))
        ) {
            // process each line in the input file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Skip empty lines
                if (line.isEmpty()) continue;

                // split the line into command and key (if present)
                String[] parts = line.split(":");
                String command = parts[0];

                try {
                    // execute command based on the input
                    switch (command) {
                        case "Insert":
                            // ensure the command is formatted correctly
                            if (parts.length != 2) {
                                output.append("Error in Line: ").append(line).append("\n");
                                continue;
                            }
                            // parse the key and insert it into the tree
                            int keyToInsert = Integer.parseInt(parts[1]);
                            boolean inserted = tree.insert(keyToInsert);
                            output.append(inserted).append("\n");
                            break;
                        case "Delete":
                            // ensure the command is correctly formatted
                            if (parts.length != 2) {
                                output.append("Error in Line: ").append(line).append("\n");
                                continue;
                            }
                            // parse the key and delete it from the tree
                            int keyToDelete = Integer.parseInt(parts[1]);
                            boolean deleted = tree.delete(keyToDelete);
                            output.append(deleted).append("\n");
                            break;
                        case "FindMin":
                            // find the minimum key in the tree
                            int min = tree.findMin();
                            output.append(min).append("\n");
                            break;
                        case "FindMax":
                            // find the maximum key in the tree
                            int max = tree.findMax();
                            output.append(max).append("\n");
                            break;
                        case "Contains":
                            // ensure the command is correctly formatted
                            if (parts.length != 2) {
                                output.append("Error in Line: ").append(line).append("\n");
                                continue;
                            }
                            // parse the key and check if it exists in the tree
                            int keyToCheck = Integer.parseInt(parts[1]);
                            boolean contains = tree.contains(keyToCheck);
                            output.append(contains).append("\n");
                            break;
                        case "PrintTree":
                            // print the tree 
                            output.append(tree.toString()).append("\n");
                            break;
                        case "Height":
                            // get the height of the tree
                            int height = tree.height();
                            output.append(height).append("\n");
                            break;
                        case "Size":
                            // get the size of the tree
                            int size = tree.size();
                            output.append(size).append("\n");
                            break;
                        default:
                            // handle unknown commands
                            output.append("Error in Line: ").append(line).append("\n");
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    // handle exceptions thrown by methods 
                    output.append("Error in ").append(command).append(": ").append(e.getMessage()).append("\n");
                }
            }

            // write the accumulated output to the output file
            writer.print(output.toString());
        } catch (FileNotFoundException e) {
            // handle file not found errors
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
