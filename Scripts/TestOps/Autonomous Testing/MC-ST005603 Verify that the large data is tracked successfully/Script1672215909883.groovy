assert false
'Pre-condition:'
'User has the tracking data is enough large to:'
'- New file is auto-created if the size of the existing one is greater than <x> Mb.'
'- A new file is auto-created depending on:'
'	-- Exceed a period of time (ex: after every 4 hours a new file is created).'
'	-- Over file size (ex: if an existing file size is 100Mb, then another new file is created).'
'The traffic-data streams into S3.'

'Description:'
'1. Log into S3.'

'2. Navigate to the Bucket layout of the traffic-data. Verify that Bucket layout displays enough data.'