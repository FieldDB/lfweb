<?php
require_once(dirname(__FILE__) . '/../testconfig.php');
require_once(SimpleTestPath . 'autorun.php');

require_once(TestPath . 'CommandTest/LiftTestEnvironment.php');
require_once(dirname(__FILE__) . '/../MockObject/AllMockObjects.php');

use \lfbase\common\DataConnector;
use \lfbase\common\DataConnection;

class TestOfUpdateDashboardDataCommand extends UnitTestCase {

	
	
	function testUpdateDashboardDataCommand_30days() {

		$e = new LiftTestEnvironment();
		$projectModel = new ProjectModelMockObject();
		//2 entry, 2 word, 2sense, 1definition, 1 partofspeech, 2examples, 1exampleform
		$e->createLiftWith(2, 2, 2, 1, 1, 2, 1);
		$command = new \commands\UpdateDashboardCommand(284, $projectModel, $e);
		$result = $command->execute();
		$this->assertEqual('ln', $result);
	}

}

?>