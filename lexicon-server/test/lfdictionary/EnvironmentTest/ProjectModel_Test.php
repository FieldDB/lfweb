<?php
use lfbase\environment\ProjectModel;
use lfbase\environment\EnvironmentMapper;
use lfbase\environment\ProjectRole;
use lfbase\environment\ProjectPermission;

require_once(dirname(__FILE__) . '/../testconfig.php');
require_once(SIMPLETEST_PATH . 'autorun.php');
require_once(LF_BASE_PATH . "/lfbase/Loader.php");
require_once(dirname(__FILE__) . '/../MockObject/AllMockObjects.php');
// require_once(TEST_PATH . 'EnvironmentTest/DrupalTestEnvironment.php');

class TestProjectModel extends UnitTestCase {

	function __construct() {
		EnvironmentMapper::connect(new LFProjectAccessMockEnvironment());
	}
	
	function testGetName_Reads() {
		$p = new ProjectModel(TestEnvironment::PROJECT_ID);
		$this->assertEqual('name', $p->getName());
	}
	
	function testGetTitle_Reads() {
		$p = new ProjectModel(TestEnvironment::PROJECT_ID);
		$this->assertEqual('title', $p->getTitle());
	}

	function testGetLanguageCode_Reads() {
		$p = new ProjectModel(TestEnvironment::PROJECT_ID);
		$this->assertEqual('ln', $p->getLanguageCode());
	}

	function testGetType_Reads() {
		$p = new ProjectModel(TestEnvironment::PROJECT_ID);
		$this->assertEqual('dictionary', $p->getType());
	}
}

?>