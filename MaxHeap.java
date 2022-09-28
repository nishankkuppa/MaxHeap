import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
	private ArrayList<Student> students;

	public MaxHeap(int capacity)
	{
		students = new ArrayList<Student>(capacity);
	}

	public MaxHeap(Collection<Student> collection)
	{
		students = new ArrayList<Student>(collection);
		
		// Set the index as the current student
		for(int i = 0; i < size(); i++) 
		{
			students.get(i).setIndex(i);
		}

		for (int i = size() / 2 - 1; i >= 0; i--)
		{
			maxHeapify(i);
		}
	}

	public Student getMax()
	{
		if (size() < 1)
		{
			throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
		}
		return students.get(0);
	}

	public Student extractMax()
	{
		Student value = getMax();
		students.set(0, students.get(size() - 1));
		students.remove(size() - 1);
		maxHeapify(0);
		return value;
	}


	
//	public int getIndex(Student elt)
//	{
//		int index = 0;
//		
//		for (int i = 0; i < size(); i++)
//		{
//			if (students.get(i).equals(elt))
//			{
//				index = i;
//			}
//		}
//		
//		return index;
//	}

	public void insert(Student elt) // throws IndexOutOfBoundsException
	{
		// Please write me. I should add the given student into the heap,
		// following the insert algorithm from the videos.

		// Begin filtering to appropriately place the new student in the arraylist

		/*
		 * compare to parent if bigger than parent, swap compare to new parent if bigger
		 * than new parent, then swap compare to new parent if not bigger than new
		 * parent, stop
		 */

		int currentIndex = students.size();						// the location of the new child in the arraylist
		
		students.add(elt);										// Add the student to the end of the arraylist
		
		elt.setIndex(currentIndex);								// Set the index to the newly placed student
		
		swapUntilComplete(currentIndex);						// Update the heap until heap property is satisfied

	}

	public void addGrade(Student elt, double gradePointsPerUnit, int units) // throws IndexOutOfBoundsException
	{
		// Please write me. I should change the student's gpa (using a method
		// from the student class), and then adjust the heap as needed using
		// the changeKey algorithm from the videos.

		

		elt.addGrade(gradePointsPerUnit, units); 				// Change the student's GPA
		
		int indexOfChild = elt.getIndex();						// the location of the new child in the arraylist

		// If the student's and parent's GPA are equal, or if the student's GPA is less
		// than the parent's GPA
		if (students.get(indexOfChild).compareTo(students.get(parent(indexOfChild))) <= 0)
		{
			maxHeapify(indexOfChild);							// Arrange the node to satisfy the max-heap property
		} 
		else
		{
			swapUntilComplete(indexOfChild);					// Update the heap until heap property is satisfied
		}

	}
	
	private void swapUntilComplete(int i)
	{
		// While the child GPA is greater than the parent GPA
		// (continues until parent GPAs are greater than child GPAs)
		while (students.get(i).compareTo(students.get(parent(i))) > 0)
		{
			swap(i, parent(i)); 			// Swap the objects
			i = parent(i); 					// Swap the reference in the arraylist
		}
	}

	private int parent(int index)
	{
		return (index - 1) / 2;
	}

	private int left(int index)
	{
		return 2 * index + 1;
	}

	private int right(int index)
	{
		return 2 * index + 2;
	}
	
	public int size()
	{
		return students.size();
	}

	private void swap(int from, int to)
	{
		Student val = students.get(from);
		val.setIndex(to);								// set the student's index as the destination
		students.set(from, students.get(to));			// insert the student into the corresponding index
		students.get(from).setIndex(from);				// change the index of the parent node
		students.set(to, val);
	}

	private void maxHeapify(int index)
	{
		int left = left(index);
		int right = right(index);
		int largest = index;
		if (left < size() && students.get(left).compareTo(students.get(largest)) > 0)
		{
			largest = left;
		}
		if (right < size() && students.get(right).compareTo(students.get(largest)) > 0)
		{
			largest = right;
		}
		if (largest != index)
		{
			swap(index, largest);
			maxHeapify(largest);
		}
	}
}