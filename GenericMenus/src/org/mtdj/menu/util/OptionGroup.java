package org.mtdj.menu.util;

import java.util.ArrayList;
import java.util.List;

public class OptionGroup {
	List<MTOptionBox> optionBoxes = new ArrayList<MTOptionBox>();
	
	public OptionGroup() {
		
	}

	public List<MTOptionBox> getOptionBoxes() {
		return optionBoxes;
	}

	public void setOptionBoxes(List<MTOptionBox> optionBoxes) {
		this.optionBoxes = optionBoxes;
	}
	
	public void addOptionBox(MTOptionBox box) {
		if (!this.optionBoxes.contains(box)) {
			this.optionBoxes.add(box);
		}
	}
	
	public void removeOptionBox(MTOptionBox box) {
		this.optionBoxes.remove(box);
	}
	
	public void setEnabled(MTOptionBox box) {
		for (MTOptionBox ob: optionBoxes) {
			if (ob != box) {
				ob.disable();
			}
			
		}
	}
	
	public short getOption() {
		short i=1;
		for (MTOptionBox ob: optionBoxes) {
			if (ob.getBooleanValue() == true) return i;
			i++;
		}
		return 0;
	}
	
	
}
