import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class BinarySearchTree {

	public static void main(String args[])
	{
		System.out.println("Input File:");
		Scanner sc = new Scanner(System.in);	
		String inputFile_str = sc.nextLine();
		sc.close();
		
		
		File InputFile = new File(inputFile_str);
		if(InputFile.exists() && !InputFile.isDirectory())
		{
			String[] commandarray= null;
			try {
				
				commandarray = loadInputFile(InputFile);
			} catch (IOException e) {
				System.out.println("File Error!\n");
				e.printStackTrace();
			}
				
			
			BST BSTree = new BST();
			for(String str:commandarray)
			{
				char operation = str.charAt(0);
				switch (operation) {
				case 'I':
					System.out.println("Insertion: " + str);
					System.out.println("-----------------------------------------------------");
					String DataInsert = str.substring(2, str.length());
					String[] TokenInsert = DataInsert.split(" ");
					
					for(String value : TokenInsert)
					{
						BSTree.insert(Integer.parseInt(value.trim()));						
					}
					System.out.println("");
					break;
				case 'D':
					System.out.println("Deletion: " + str);
					System.out.println("-----------------------------------------------------");
					String DataDelete = str.substring(2, str.length());
					String[] TokenDelete = DataDelete.split(" ");						for(String value : TokenDelete)
					
{
						BSTree.delete(Integer.parseInt(value.trim()));						
					}					
					System.out.println("");
					break;
				case 'S':
					System.out.println("Search: " + str);
					System.out.println("-----------------------------------------------------");
					int value = Integer.parseInt(str.substring(2, str.length()).trim());
					if(BSTree.search(value))				
						System.out.println("Node "+value+" is present in the BST.");					
					else
						System.out.println("Node "+value+" is not present in the BST.");
					System.out.println("");
					break;
				case 'P':
					System.out.println("Print (Inorder Traversal)");
					System.out.println("-----------------------------------------------------");
					BSTree.inorder();
					System.out.println("");
					break;
				default:
					System.out.println("Error!");
					System.out.println("Ending Program Execution!");
					System.exit(0);
					break;
				}
			}
		}
		else
		{
			System.out.println("Error! Enter a Valid File.");
		}
	}
	
	private static String[] loadInputFile(File file_in) throws IOException
	{
		
		LineNumberReader  lnr = new LineNumberReader(new FileReader(file_in));
		lnr.skip(Long.MAX_VALUE);
		int NumberLines= lnr.getLineNumber() + 1;
		String[] commandarray = new String[NumberLines];
		lnr.close();
		
		
		FileReader filereader = new FileReader(file_in);
		BufferedReader	bufferedreader = new BufferedReader(filereader);
		String line;
		int lineindex = 0;
		while((line = bufferedreader.readLine()) != null)
		{
			commandarray[lineindex] = line;
			lineindex++;
		}


		bufferedreader.close();
		return commandarray;
	}
}

class BSTNode
{
    BSTNode left, right;
    int value;

    
    public BSTNode()
    {
        left = null;
        right = null;
        value = 0;
    }
    
    
    public BSTNode(int n)
    {
        left = null;
        right = null;
        value = n;
    }
    
    
    public void setLeft(BSTNode node)
    {
        left = node;
    }
    
    
    public void setRight(BSTNode node)
    {
        right = node;
    }
    
    
    public BSTNode getLeft()
    {
        return left;
    }
    
    
    public BSTNode getRight()
    {
        return right;
    }
    
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
    
    public int getValue()
    {
        return value;
    }     
}


class BST
{
	 private BSTNode root;
	 
     public BST()
     {
         root = null;
     }
     
     public boolean isEmpty()
     {
         return root == null;
     }
     
     public void insert(int data)
     {
    	 if(!search(data))
    	 {
    		 root = insert(root, data);
    		 System.out.println(" Inserted Successfully"+data);
    	 }
    	 else
    	 {
    		 System.out.println("Insertion "+data+" can’t completed. It is already present in the BST.");
    	 }
     }
     
     private BSTNode insert(BSTNode node, int data)
     {
         if (node == null)
             node = new BSTNode(data);
         else
         {
             if (data <= node.getValue())
                 node.left = insert(node.left, data);
             else
                 node.right = insert(node.right, data);
         }
         return node;
     }
     
     public boolean search(int val)
     {
         return search(root, val);
     }
     
     private boolean search(BSTNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.getValue();
             if (val < rval)
                 r = r.getLeft();
             else if (val > rval)
                 r = r.getRight();
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         return found;
     }
     
     public void inorder()
     {
         printInorder(root);
         System.out.println("");
     }
     
     private void printInorder(BSTNode r)
     {
	    if(r != null){
	        System.out.print("(");
	        printInorder(r.left);
	        System.out.print(r.getValue() + " ");
	        printInorder(r.right);
	        System.out.print(")");
	    }
    }
     
     public void delete(int DN)
     {
         if (isEmpty())
             System.out.println("Tree is Empty.");
         else if (search(DN) == false)
             System.out.println("Deletion "+ DN +" can’t be completed. It is not present in the BST.");
         else
         {
             root = delete(root, DN);
             System.out.println(DN+ " deleted from the tree");
         }
     }
     private BSTNode delete(BSTNode root, int k)
     {
         BSTNode p, p2, n;
         if (root.getValue() == k)
         {
             BSTNode lt, rt;
             lt = root.getLeft();
             rt = root.getRight();
             if (lt == null && rt == null)
                 return null;
             else if (lt == null)
             {
                 p = rt;
                 return p;
             }
             else if (rt == null)
             {
                 p = lt;
                 return p;
             }
             else
             {
                 p2 = rt;
                 p = rt;
                 while (p.getLeft() != null)
                     p = p.getLeft();
                 p.setLeft(lt);
                 return p2;
             }
         }

         if (k < root.getValue())
         {
             n = delete(root.getLeft(), k);
             root.setLeft(n);
         }
         else
         {
             n = delete(root.getRight(), k);
             root.setRight(n);             
         }
         return root;
     }
}
