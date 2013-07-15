<?php
namespace libraries\lfdictionary\environment;

use libraries\lfdictionary\common\DataConnector;
use libraries\lfdictionary\common\DataConnection;
use libraries\lfdictionary\common\LoggerFactory;

class LanguageForgEnvironmentFixer {
	
	/**
	 * @param DrupamEnvironmentMapper $mapper
	 * @param ProjectAccess $projectAccess
	 */
	public static function fixProjectAccess($mapper, $projectAccess) {
		$db = DataConnector::connect();
		$sql = sprintf("SELECT uid FROM node WHERE type='project' AND nid=%d", $projectAccess->projectId);
		$result = $db->execute($sql);
		$row = $db->fetchrow($result);
		if ($row) {
			$uidProjectOwner = $row['uid'];
			if ($uidProjectOwner == $projectAccess->userId) {
				$projectAccess->setRole(ProjectRole::ADMIN);
				$mapper->writeProjectAccess($projectAccess);
				LoggerFactory::getLogger()->logInfoMessage(sprintf("Fix Project Access: uid %d set as admin", $projectAccess->userId));
			} else {
				LoggerFactory::getLogger()->logInfoMessage(sprintf("Fix Project Access Error: uid %d is not the owner, %d is.", $projectAccess->userId, $row['uid']));
			}
		}
	}
	
}

?>