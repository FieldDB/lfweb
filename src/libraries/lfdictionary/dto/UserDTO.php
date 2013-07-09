<?php
namespace libraries\lfdictionary\dto;

/**
 * This class contains User DTO
 */
class UserDTO {

	/**
	 * @var UserModel
	 */
	private $_userModel;
	
	/**
	* @var String
	*/
	private $_userRole;
	/**
	 * @param UserModel $userModel
	 */
	function __construct($userModel) {
		$this->_userModel = $userModel;
	}

	public function setUserRole($role)
	{
		$this->_userRole=$role;
	}
	
	public function getUserId()
	{
		return (int)$this->_userModel->id();
	}
	
	/**
	 * Encodes the object into a php array, suitable for use with json_encode
	 * @return array
	 */
	function encode() {
		return array(
 			'id' => (int)$this->_userModel->id(),
 			'name' => $this->_userModel->getUserName(),
			'role' => $this->_userRole
		);

	}
}
?>