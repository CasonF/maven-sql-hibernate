package hibAssignment;
import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@Column(name = "task_name")
	public String task;
	@Column(name = "doneness")
	public boolean isDone;
	
	public Item(String task, boolean isDone)
	{
		this.task = task;
		this.isDone = isDone;
	}
	
	public Item(String task)
	{
		this.task = task;
		isDone = false;
	}
	
	public Item()
	{
		task = null;
		isDone = false;
	}
	
	public boolean IsDone()
	{
		return isDone;
	}
	
	public String GetTaskString()
	{
		return task;
	}
	
	public void SetDoneness(Item item, boolean doneness)
	{
		item.isDone = doneness;
	}
	
	public Item FindItem(String itemName)
	{
		Item found = null;
		if (itemName.equals(this.task))
		{
			found = this;
		}
		return found;
	}

}
