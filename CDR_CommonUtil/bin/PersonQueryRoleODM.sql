select a.MAILFLG          isMailAddressFlag,
       a.REAUTHEMAILFLAG  isReauthEmailFlag,
       a.REAUTHPHONEFLAG  isReauthPhoneFlag,
       a.REAUTHFAXFLAG    isReauthFaxFlag,
       a.REAUTHVISITFLAG  isReauthVisitFlag

  from  PRESCRIBER_ADDRESS_FLAG a
 where a.PRESCRIBERADDRKEY = ?
  