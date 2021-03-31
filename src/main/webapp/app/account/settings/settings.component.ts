import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { JhiLanguageService } from 'ng-jhipster';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { LANGUAGES } from 'app/core/language/language.constants';
import { Client, IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-settings',
  templateUrl: './settings.component.html',
})
export class SettingsComponent implements OnInit {
  client: IClient = new Client();
  account!: Account;
  success = false;
  languages = LANGUAGES;
  settingsForm = this.fb.group({
    firstName: [undefined, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
    lastName: [undefined, [Validators.required, Validators.minLength(1), Validators.maxLength(50)]],
    email: [undefined, [Validators.required, Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    tel: [null, [Validators.required, Validators.minLength(0), Validators.maxLength(15)]],
    langKey: [undefined],
  });

  constructor(
    private accountService: AccountService,
    private clientService: ClientService,
    private fb: FormBuilder,
    private languageService: JhiLanguageService
  ) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => {
      if (account) {
        this.settingsForm.patchValue({
          firstName: account.firstName,
          lastName: account.lastName,
          email: account.email,
          langKey: account.langKey,
        });

        this.account = account;
      }
    });
    this.clientService.findByUserId(this.account.id).subscribe(client => {
      if (client.status === 200) {
        this.settingsForm.patchValue({
          tel: client.body?.tel,
        });

        (this.client.id = client.body?.id),
          (this.client.isShopOwner = client.body?.isShopOwner),
          (this.client.shops = client.body?.shops),
          (this.client.tel = client.body?.tel),
          (this.client.user = client.body?.user);
      }
    });
  }

  save(): void {
    this.success = false;

    this.account.firstName = this.settingsForm.get('firstName')!.value;
    this.account.lastName = this.settingsForm.get('lastName')!.value;
    this.account.email = this.settingsForm.get('email')!.value;
    this.account.langKey = this.settingsForm.get('langKey')!.value;

    this.client.tel = this.settingsForm.get('tel')!.value;
    this.client.user = this.account;

    this.accountService.save(this.account).subscribe(() => {
      this.accountService.authenticate(this.account);

      if (this.account.langKey !== this.languageService.getCurrentLanguage()) {
        this.languageService.changeLanguage(this.account.langKey);
      }
    });

    if (this.client.id !== undefined) {
      this.clientService.update(this.client).subscribe(() => {
        this.success = true;
      });
    } else {
      this.clientService.create(this.client).subscribe(() => {
        this.success = true;
      });
    }
  }
}
