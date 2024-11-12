package nirmalya.aatithya.restmodule.api.model;

public class BlockUserAPIModel {

	private String assignedBlock;
	private String blockId;

	public BlockUserAPIModel() {
		super();
	}

	public BlockUserAPIModel(Object assignedBlock,Object blockId) {
		super();
		try {
			this.assignedBlock = (String) assignedBlock;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.blockId = (String) blockId;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAssignedBlock() {
		return assignedBlock;
	}

	public void setAssignedBlock(String assignedBlock) {
		this.assignedBlock = assignedBlock;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	
	
	
	

}
