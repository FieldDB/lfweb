<?php
use lfbase\environment\ProjectRole;
use lfbase\environment\IEnvironmentMapper;
use lfbase\environment\ProjectPermission;
use lfbase\environment\ProjectAccess;

require_once(dirname(__FILE__) . '/../testconfig.php');

class ProjectAccessMockEnvironment implements IEnvironmentMapper {

	public function __construct() {
		ProjectRole::add('admin', new ProjectPermission(ProjectPermission::CAN_ADMIN), 1, 1);
		ProjectRole::add('user', new ProjectPermission(ProjectPermission::CAN_EDIT_ENTRY), 1, 1);
	}

	public function writeProjectAccess($projectAccess) {
	}

	public function readProjectAccess($projectAccess) {
		$projectAccess->setRole('admin');
	}
	
	/**
	 * @param ProjectModel $project
	 */
	public function readProject($project) {
		$project->set('title', 'ln', 'name', 'dictionary');
	}

	public function writeProject($project) {
	}

	/**
	* @param UserModel $user
	*/
	public function readUser($user) {
		$user->set('username');
	}

	public function writeUser($userId, $userName, $firstName, $lastName, $email)
	{
		
	}
	
	public function searchUser($name, $indexBegin, $indexEnd){
	}

	public function userExists($userName){
	}

	public function emailExists($email){
	}

	public function getUserByName($username){
	}

	public function createNewUser($username, $pwd, $email){
	}

	public function removeUserFromProject($projectId, $userId)
	{

	}
	
	public function getProjectByName($name){}
	
	public function createNewProject($name){}
	
	public function projectExists($name){}
	
	public function setUserPassword($userId, $password){}
	
	public function addUserToProject($projectId, $userId, $roleId){}
	public function listUsersInProject($projectId)
	{}
	public function getUsersInProjectByRole($projectId, $hostRole) {
	}
}
?>